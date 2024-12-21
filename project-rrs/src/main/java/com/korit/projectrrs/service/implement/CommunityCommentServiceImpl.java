package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.communitycomment.request.CommunityCommentCreateRequestDto;
import com.korit.projectrrs.dto.communitycomment.request.CommunityCommentUpdateRequestDto;
import com.korit.projectrrs.dto.communitycomment.response.CommunityCommentResponseDto;
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

    private final CommunityCommentRepository communityCommentRepository;
    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponseDto<CommunityCommentResponseDto> createComment(Long userId, CommunityCommentCreateRequestDto dto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(ResponseMessage.USER_NOT_FOUND));
        CommunityComment comment = CommunityComment.builder()
                .user(user)
                .community(communityRepository.findById(dto.getCommunityId()).orElseThrow(() -> new RuntimeException(ResponseMessage.COMMUNITY_NOT_FOUND)))
                .communityCommentContent(dto.getCommunityCommentContent())
                .build();

        CommunityComment savedComment = communityCommentRepository.save(comment);
        return ResponseDto.setSuccess(ResponseMessage.COMMENT_CREATED_SUCCESSFULLY, new CommunityCommentResponseDto(savedComment));
    }

    @Override
    @Transactional
    public ResponseDto<CommunityCommentResponseDto> updateComment(Long userId, CommunityCommentUpdateRequestDto dto) {
        CommunityComment comment = communityCommentRepository.findById(dto.getCommentId())
                .orElseThrow(() -> new RuntimeException(ResponseMessage.NOT_EXIST_COMMENT));

        if (!comment.getUser().getUserId().equals(userId)) {
            throw new RuntimeException(ResponseMessage.NOT_AUTHORIZED_TO_UPDATE_COMMENT);
        }

        comment.setCommunityCommentContent(dto.getCommunityCommentContent());
        communityCommentRepository.save(comment);
        return ResponseDto.setSuccess(ResponseMessage.COMMENT_UPDATED_SUCCESSFULLY, new CommunityCommentResponseDto(comment));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommunityCommentResponseDto> getCommentsByCommunity(Long communityId) {
        List<CommunityComment> comments = communityCommentRepository.findByCommunityCommunityId(communityId);
        return comments.stream().map(CommunityCommentResponseDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteComment(Long userId, Long commentId) {
        CommunityComment comment = communityCommentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException(ResponseMessage.NOT_EXIST_COMMENT));

        if (!comment.getUser().getUserId().equals(userId)) {
            throw new RuntimeException(ResponseMessage.NOT_AUTHORIZED_TO_DELETE_COMMENT);
        }

        communityCommentRepository.delete(comment);
    }
}
