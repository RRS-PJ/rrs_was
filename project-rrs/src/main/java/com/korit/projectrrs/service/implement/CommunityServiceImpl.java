package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.community.request.CommunityCreateRequestDto;
import com.korit.projectrrs.dto.community.request.CommunityUpdateRequestDto;
import com.korit.projectrrs.dto.community.response.CommunityResponseAllDto;
import com.korit.projectrrs.dto.community.response.CommunityResponseDto;
import com.korit.projectrrs.entity.Community;
import com.korit.projectrrs.entity.CommunityAttachment;
import com.korit.projectrrs.entity.CommunityLikes;
import com.korit.projectrrs.entity.User;
import com.korit.projectrrs.repositoiry.CommunityAttachmentRepository;
import com.korit.projectrrs.repositoiry.CommunityLikesRepository;
import com.korit.projectrrs.repositoiry.CommunityRepository;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.service.CommunityService;
import com.korit.projectrrs.service.FileService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;
    private final CommunityLikesRepository communityLikesRepository;
    private final CommunityAttachmentRepository communityAttachmentRepository;
    private final FileService fileService;

    @Override
    @Transactional
    public ResponseDto<CommunityResponseDto> createCommunity(Long userId, CommunityCreateRequestDto dto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.USER_NOT_FOUND);
        }

        User user = optionalUser.get();

        String thumbnailUrl = "default-thumbnail.jpg";
        if (dto.getCommunityThumbnailFile() != null && !dto.getCommunityThumbnailFile().isEmpty()) {
            String fileName = fileService.uploadFile(dto.getCommunityThumbnailFile(), "community-thumbnail");
            if (fileName != null) {
                thumbnailUrl = fileName;
            }
        }

        Community community = Community.builder()
                .user(user)
                .communityTitle(dto.getCommunityTitle())
                .communityContent(dto.getCommunityContent())
                .communityThumbnailUrl(thumbnailUrl)
                .build();

        communityRepository.save(community);

        List<MultipartFile> multifiles = dto.getFiles();
        List<String> fileNames = new ArrayList<>();
        if (multifiles != null && !multifiles.isEmpty()) {
            for (MultipartFile multiFile : multifiles) {
                String fileName = fileService.uploadFile(multiFile, "community");
                if (fileName != null) {
                    fileNames.add(fileName);
                    CommunityAttachment attachment = new CommunityAttachment();
                    attachment.setCommunity(community);
                    attachment.setCommunityAttachment(fileName);
                    communityAttachmentRepository.save(attachment);
                }
            }
        }

        CommunityResponseDto responseDto = new CommunityResponseDto(community);
        responseDto.setAttachments(fileNames);

        return ResponseDto.setSuccess(ResponseMessage.COMMUNITY_CREATED_SUCCESSFULLY, responseDto);
    }

    @Override
    @Transactional
    public ResponseDto<CommunityResponseDto> updateCommunity(Long userId, Long communityId, CommunityUpdateRequestDto dto) {
        Optional<Community> optionalCommunity = communityRepository.findById(communityId);
        if (optionalCommunity.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.COMMUNITY_NOT_FOUND);
        }

        Community community = optionalCommunity.get();
        if (!community.getUser().getUserId().equals(userId)) {
            return ResponseDto.setFailed(ResponseMessage.NOT_AUTHORIZED_TO_UPDATE);
        }

        String defaultThumbnailUrl = "default-thumbnail.jpg";
        String thumbnailUrl = community.getCommunityThumbnailUrl();
        if (dto.getCommunityThumbnailFile() != null && !dto.getCommunityThumbnailFile().isEmpty()) {
            if (!"default-thumbnail.jpg".equals(thumbnailUrl)) {
                fileService.removeFile(thumbnailUrl);
            }
            String fileName = fileService.uploadFile(dto.getCommunityThumbnailFile(), "community-thumbnail");
            thumbnailUrl = fileName != null ? fileName : defaultThumbnailUrl;
        } else {
            if (!"default-thumbnail.jpg".equals(thumbnailUrl)) {
                fileService.removeFile(thumbnailUrl);
            }
            thumbnailUrl = defaultThumbnailUrl;
        }

        community.updateDetails(dto.getCommunityTitle(), dto.getCommunityContent(), thumbnailUrl);

        List<CommunityAttachment> currentAttachments = communityAttachmentRepository.findByCommunityCommunityId(community.getCommunityId());
        for (CommunityAttachment attachment : currentAttachments) {
            fileService.removeFile(attachment.getCommunityAttachment());
            communityAttachmentRepository.delete(attachment);
        }

        List<String> fileNames = new ArrayList<>();
        if (dto.getFiles() != null && !dto.getFiles().isEmpty()) {
            for (MultipartFile file : dto.getFiles()) {
                String fileName = fileService.uploadFile(file, "community");
                if (fileName != null) {
                    CommunityAttachment newAttachment = CommunityAttachment.builder()
                            .community(community)
                            .communityAttachment(fileName)
                            .build();
                    communityAttachmentRepository.save(newAttachment);
                    fileNames.add(fileName);
                }
            }
        }

        communityRepository.save(community);

        CommunityResponseDto responseDto = new CommunityResponseDto(community);
        responseDto.setAttachments(fileNames);

        return ResponseDto.setSuccess(ResponseMessage.COMMUNITY_UPDATED_SUCCESSFULLY, responseDto);
    }

    @Override
    @Transactional
    public ResponseDto<Void> deleteCommunity(Long userId, Long communityId) {
        Optional<Community> optionalCommunity = communityRepository.findById(communityId);
        if (optionalCommunity.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.COMMUNITY_NOT_FOUND);
        }

        Community community = optionalCommunity.get();
        if (!community.getUser().getUserId().equals(userId)) {
            return ResponseDto.setFailed(ResponseMessage.NOT_AUTHORIZED_TO_DELETE);
        }

        communityRepository.delete(community);
        return ResponseDto.setSuccess(ResponseMessage.COMMUNITY_DELETED_SUCCESSFULLY, null);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<CommunityResponseAllDto>> getAllCommunities() {
        List<Community> communities = communityRepository.findAll();
        List<CommunityResponseAllDto> response = communities.stream()
                .map(CommunityResponseAllDto::new)
                .collect(Collectors.toList());
        return ResponseDto.setSuccess(ResponseMessage.COMMUNITY_FETCHED_SUCCESSFULLY, response);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<CommunityResponseDto> getCommunity(Long communityId) {
        Optional<Community> optionalCommunity = communityRepository.findById(communityId);
        if (optionalCommunity.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.COMMUNITY_NOT_FOUND);
        }

        Community community = optionalCommunity.get();

        // Initialize lazy fields
        Hibernate.initialize(community.getUserLiked());
        Hibernate.initialize(community.getComments());
        Hibernate.initialize(community.getAttachments());

        CommunityResponseDto responseDto = new CommunityResponseDto(community);

        return ResponseDto.setSuccess(ResponseMessage.COMMUNITY_FETCHED_SUCCESSFULLY, responseDto);
    }

    @Override
    @Transactional
    public ResponseDto<Map<String, Object>> toggleLike(Long userId, Long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException(ResponseMessage.COMMUNITY_NOT_FOUND));

        if (community.getUser().getUserId().equals(userId)) {
            return ResponseDto.setFailed(ResponseMessage.NOT_AUTHORIZED_TO_TOGGLE_LIKE);
        }

        Optional<CommunityLikes> existingLike = communityLikesRepository.findByCommunityCommunityIdAndUserUserId(communityId, userId);
        boolean userLiked;

        if (existingLike.isPresent()) {
            CommunityLikes like = existingLike.get();
            if (like.isUserLiked()) {
                // 좋아요를 취소
                if (community.getCommunityLikeCount() > 0) { // 좋아요 수가 0 이하로 내려가지 않도록 제한
                    community.updateLikeCount(community.getCommunityLikeCount() - 1);
                }
                like.setUserLiked(false);
                communityLikesRepository.save(like);
                userLiked = false;
            } else {
                // 다시 좋아요 추가
                like.setUserLiked(true);
                community.updateLikeCount(community.getCommunityLikeCount() + 1);
                communityLikesRepository.save(like);
                userLiked = true;
            }
        } else {
            // 처음 좋아요를 추가
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException(ResponseMessage.USER_NOT_FOUND));

            CommunityLikes newLike = CommunityLikes.builder()
                    .community(community)
                    .user(user)
                    .userLiked(true)
                    .build();
            communityLikesRepository.save(newLike);

            community.updateLikeCount(community.getCommunityLikeCount() + 1);
            userLiked = true;
        }

        communityRepository.save(community);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("likeCount", community.getCommunityLikeCount());
        responseData.put("userLiked", userLiked);
        responseData.put("userId", userId); // userId 추가

        return ResponseDto.setSuccess(ResponseMessage.LIKE_TOGGLE_SUCCESS, responseData);
    }

}
