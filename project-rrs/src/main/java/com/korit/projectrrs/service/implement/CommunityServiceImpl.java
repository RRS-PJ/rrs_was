package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.constant.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.community.request.CommunityCreateRequestDto;
import com.korit.projectrrs.dto.community.request.CommunityUpdateRequestDto;
import com.korit.projectrrs.dto.community.response.CommunityResponseAllDto;
import com.korit.projectrrs.dto.community.response.CommunityResponseDto;
import com.korit.projectrrs.entity.Community;
import com.korit.projectrrs.entity.CommunityAttachment;
import com.korit.projectrrs.entity.User;
import com.korit.projectrrs.repositoiry.CommunityAttachmentRepository;
import com.korit.projectrrs.repositoiry.CommunityRepository;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.service.CommunityService;
import com.korit.projectrrs.service.FileService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;
    private final CommunityAttachmentRepository communityAttachmentRepository;
    private final FileService fileService;

    @Override
    @Transactional
    public ResponseDto<CommunityResponseDto> createCommunity(Long userId, CommunityCreateRequestDto dto) {
        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.USER_NOT_FOUND);
            }

            User user = optionalUser.get();

            String thumbnailFile = null;
            if (dto.getCommunityThumbnailFile() != null && !dto.getCommunityThumbnailFile().isEmpty()) {
                thumbnailFile = fileService.uploadFile(dto.getCommunityThumbnailFile(), "community-thumbnail");
            }

            Community community = Community.builder()
                    .user(user)
                    .communityTitle(dto.getCommunityTitle())
                    .communityContent(dto.getCommunityContent())
                    .communityThumbnailFile(thumbnailFile)
                    .build();

            communityRepository.save(community);

            List<MultipartFile> multifiles = dto.getFiles();
            List<String> fileNames = new ArrayList<>();
            if (multifiles != null && !multifiles.isEmpty()) {
                for (MultipartFile multiFile : multifiles) {
                    String uploadedFileName = fileService.uploadFile(multiFile, "community");
                    if (uploadedFileName != null) {
                        fileNames.add(uploadedFileName);
                        CommunityAttachment attachment = new CommunityAttachment();
                        attachment.setCommunity(community);
                        attachment.setCommunityAttachment(uploadedFileName);
                        communityAttachmentRepository.save(attachment);
                    }
                }
            }

            CommunityResponseDto responseDto = new CommunityResponseDto(community);
            responseDto.setAttachments(fileNames);

            return ResponseDto.setSuccess(ResponseMessage.COMMUNITY_CREATED_SUCCESSFULLY, responseDto);
        } catch (Exception e) {
            return ResponseDto.setFailed(ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseDto<CommunityResponseDto> updateCommunity(Long userId, Long communityId, CommunityUpdateRequestDto dto) {
        try {

            Optional<Community> optionalCommunity = communityRepository.findById(communityId);
            if (optionalCommunity.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.COMMUNITY_NOT_FOUND);
            }

            Community community = optionalCommunity.get();
            if (!community.getUser().getUserId().equals(userId)) {
                return ResponseDto.setFailed(ResponseMessage.NOT_AUTHORIZED_TO_UPDATE);
            }

            String thumbnailFile = community.getCommunityThumbnailFile();
            if (dto.getCommunityThumbnailFile() != null && !dto.getCommunityThumbnailFile().isEmpty()) {
                if (thumbnailFile != null) {
                    fileService.removeFile(thumbnailFile);
                }
                thumbnailFile = fileService.uploadFile(dto.getCommunityThumbnailFile(), "community-thumbnail");
            }

            community.updateDetails(dto.getCommunityTitle(), dto.getCommunityContent(), thumbnailFile);

            List<String> fileNames = new ArrayList<>();
            if (dto.getFiles() != null && !dto.getFiles().isEmpty()) {
                for (MultipartFile file : dto.getFiles()) {
                    String uploadedFileName = fileService.uploadFile(file, "community");
                    CommunityAttachment newAttachment = CommunityAttachment.builder()
                            .community(community)
                            .communityAttachment(uploadedFileName)
                            .build();
                    communityAttachmentRepository.save(newAttachment);
                    fileNames.add(uploadedFileName);
                }
            }

            communityRepository.save(community);

            CommunityResponseDto responseDto = new CommunityResponseDto(community);
            responseDto.setAttachments(fileNames);

            return ResponseDto.setSuccess(ResponseMessage.COMMUNITY_UPDATED_SUCCESSFULLY, responseDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseDto<Void> deleteCommunity(Long userId, Long communityId) {
        try {
            Optional<Community> optionalCommunity = communityRepository.findById(communityId);
            if (optionalCommunity.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.COMMUNITY_NOT_FOUND);
            }

            Community community = optionalCommunity.get();
            if (!community.getUser().getUserId().equals(userId)) {
                return ResponseDto.setFailed(ResponseMessage.NOT_AUTHORIZED_TO_DELETE);
            }

            List<CommunityAttachment> attachments = communityAttachmentRepository.findByCommunityCommunityId(communityId);
            for (CommunityAttachment attachment : attachments) {
                fileService.removeFile(attachment.getCommunityAttachment());
                communityAttachmentRepository.delete(attachment);
            }

            String thumbnailFile = community.getCommunityThumbnailFile();
            if (thumbnailFile != null) {
                fileService.removeFile(thumbnailFile);
            }

            communityRepository.delete(community);
            return ResponseDto.setSuccess(ResponseMessage.COMMUNITY_DELETED_SUCCESSFULLY, null);
        } catch (Exception e) {
            return ResponseDto.setFailed(ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<CommunityResponseAllDto>> getAllCommunities() {
        try {
            List<Community> communities = communityRepository.findAll();
            List<CommunityResponseAllDto> response = communities.stream()
                    .map(CommunityResponseAllDto::new)
                    .collect(Collectors.toList());
            return ResponseDto.setSuccess(ResponseMessage.COMMUNITY_FETCHED_SUCCESSFULLY, response);
        } catch (Exception e) {
            return ResponseDto.setFailed(ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<CommunityResponseDto> getCommunity(Long communityId) {
        try {
            Optional<Community> optionalCommunity = communityRepository.findById(communityId);
            if (optionalCommunity.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.COMMUNITY_NOT_FOUND);
            }

            Community community = optionalCommunity.get();

            Hibernate.initialize(community.getUserLiked());
            Hibernate.initialize(community.getComments());
            Hibernate.initialize(community.getAttachments());

            CommunityResponseDto responseDto = new CommunityResponseDto(community);

            return ResponseDto.setSuccess(ResponseMessage.COMMUNITY_FETCHED_SUCCESSFULLY, responseDto);
        } catch (Exception e) {
            return ResponseDto.setFailed(ResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }
}