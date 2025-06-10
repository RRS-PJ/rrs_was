package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.constant.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.communityLike.response.CommunityLikeResponseDto;
import com.korit.projectrrs.entity.Community;
import com.korit.projectrrs.entity.CommunityLikes;
import com.korit.projectrrs.entity.User;
import com.korit.projectrrs.repositoiry.CommunityLikesRepository;
import com.korit.projectrrs.repositoiry.CommunityRepository;
import com.korit.projectrrs.service.CommunityLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityLikeServiceImpl implements CommunityLikeService {

    private final CommunityRepository communityRepository;
    private final CommunityLikesRepository communityLikesRepository;

    @Override
    @Transactional
    public ResponseDto<Map<String, Object>> toggleLike(User user, Long communityId) {
        Optional<Community> optionalCommunity = communityRepository.findById(communityId);
        if (optionalCommunity.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.COMMUNITY_NOT_FOUND);
        }

        Community community = optionalCommunity.get();

        if (community.getUser().getUserId().equals(user.getUserId())) {
            return ResponseDto.setFailed(ResponseMessage.NOT_AUTHORIZED_TO_TOGGLE_LIKE);
        }

        Optional<CommunityLikes> existingLike = communityLikesRepository
                .findByCommunityCommunityIdAndUserUserId(communityId, user.getUserId());
        boolean userLiked;

        if (existingLike.isPresent()) {
            CommunityLikes like = existingLike.get();
            if (like.isUserLiked()) {
                if (community.getCommunityLikeCount() > 0) {
                    community.updateLikeCount(community.getCommunityLikeCount() - 1);
                }
                like.setUserLiked(false);
                communityLikesRepository.save(like);
                userLiked = false;
            } else {
                like.setUserLiked(true);
                community.updateLikeCount(community.getCommunityLikeCount() + 1);
                communityLikesRepository.save(like);
                userLiked = true;
            }
        } else {
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
        responseData.put("nickname", user.getNickname());

        return ResponseDto.setSuccess(ResponseMessage.LIKE_TOGGLE_SUCCESS, responseData);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<CommunityLikeResponseDto>> getUsersWhoLikedCommunity(Long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException(ResponseMessage.COMMUNITY_NOT_FOUND));

        List<CommunityLikes> likes = communityLikesRepository.findByCommunityCommunityIdAndUserLikedTrue(communityId);

        List<CommunityLikeResponseDto> response = likes.stream()
                .map(like -> new CommunityLikeResponseDto(
                        community.getCommunityId(),
                        like.getUser().getNickname()
                ))
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.USERS_FETCHED_SUCCESSFULLY, response);
    }
}
