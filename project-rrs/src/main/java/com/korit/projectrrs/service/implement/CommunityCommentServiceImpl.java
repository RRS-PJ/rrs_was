package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.constant.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.communitycomment.request.CommunityCommentCreateRequestDto;
import com.korit.projectrrs.dto.communitycomment.request.CommunityCommentUpdateRequestDto;
import com.korit.projectrrs.dto.communitycomment.response.CommunityCommentResponseDto;
import com.korit.projectrrs.entity.Community;
import com.korit.projectrrs.entity.CommunityComment;
import com.korit.projectrrs.entity.User;
import com.korit.projectrrs.repositoiry.CommunityCommentRepository;
import com.korit.projectrrs.repositoiry.CommunityRepository;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.service.CommunityCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityCommentServiceImpl implements CommunityCommentService {

    private final CommunityCommentRepository commentRepository;
    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponseDto<CommunityCommentResponseDto> createComment(Long userId, CommunityCommentCreateRequestDto dto) {
        if (dto.getCommunityCommentContent() == null || dto.getCommunityCommentContent().isBlank()) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_COMMENT_CONTENT);
        }

        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException(ResponseMessage.USER_NOT_FOUND));

            Community community = communityRepository.findById(dto.getCommunityId())
                    .orElseThrow(() -> new RuntimeException(ResponseMessage.COMMUNITY_NOT_FOUND));

            CommunityComment comment = CommunityComment.builder()
                    .community(community)
                    .user(user)
                    .communityCommentContent(dto.getCommunityCommentContent())
                    .build();

            commentRepository.save(comment);
            return ResponseDto.setSuccess(ResponseMessage.COMMENT_CREATED_SUCCESSFULLY, new CommunityCommentResponseDto(comment));

        } catch (RuntimeException e) {
            return ResponseDto.setFailed(e.getMessage());
        } catch (Exception e) {
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseDto<CommunityCommentResponseDto> updateComment(Long userId, CommunityCommentUpdateRequestDto dto) {
        if (dto.getCommunityCommentContent() == null || dto.getCommunityCommentContent().isBlank()) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_COMMENT_CONTENT);
        }

        try {
            CommunityComment comment = commentRepository.findById(dto.getCommentId())
                    .orElseThrow(() -> new RuntimeException(ResponseMessage.NOT_EXIST_COMMENT));

            if (!comment.getCommunity().getCommunityId().equals(dto.getCommunityId())) {
                return ResponseDto.setFailed(ResponseMessage.COMMENT_NOT_BELONG_TO_COMMUNITY);
            }

            if (!comment.getUser().getUserId().equals(userId)) {
                return ResponseDto.setFailed(ResponseMessage.NOT_AUTHORIZED_TO_UPDATE_COMMENT);
            }

            comment.setCommunityCommentContent(dto.getCommunityCommentContent());
            commentRepository.save(comment);
            return ResponseDto.setSuccess(ResponseMessage.COMMENT_UPDATED_SUCCESSFULLY, new CommunityCommentResponseDto(comment));

        } catch (RuntimeException e) {
            return ResponseDto.setFailed(e.getMessage());
        } catch (Exception e) {
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<CommunityCommentResponseDto>> getCommentsByCommunity(Long communityId) {
        try {
            Community community = communityRepository.findById(communityId)
                    .orElseThrow(() -> new RuntimeException(ResponseMessage.COMMUNITY_NOT_FOUND));

            List<CommunityCommentResponseDto> comments = community.getComments().stream()
                    .map(CommunityCommentResponseDto::new)
                    .collect(Collectors.toList());
            return ResponseDto.setSuccess(ResponseMessage.COMMENT_FETCHED_SUCCESSFULLY, comments);

        } catch (RuntimeException e) {
            return ResponseDto.setFailed(e.getMessage());
        } catch (Exception e) {
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseDto<Void> deleteCommentFromCommunity(Long userId, Long communityId, Long commentId) {
        try {
            CommunityComment comment = commentRepository.findById(commentId)
                    .orElseThrow(() -> new RuntimeException(ResponseMessage.NOT_EXIST_COMMENT));

            if (!comment.getCommunity().getCommunityId().equals(communityId)) {
                return ResponseDto.setFailed(ResponseMessage.COMMENT_NOT_BELONG_TO_COMMUNITY);
            }

            if (!comment.getUser().getUserId().equals(userId)) {
                return ResponseDto.setFailed(ResponseMessage.NOT_AUTHORIZED_TO_DELETE_COMMENT);
            }

            commentRepository.delete(comment);
            return ResponseDto.setSuccess(ResponseMessage.COMMENT_DELETED_SUCCESSFULLY, null);

        } catch (RuntimeException e) {
            return ResponseDto.setFailed(e.getMessage());
        } catch (Exception e) {
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }
}
