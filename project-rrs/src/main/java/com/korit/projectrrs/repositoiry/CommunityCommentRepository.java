package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.Community;
import com.korit.projectrrs.entity.CommunityComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityCommentRepository extends JpaRepository<CommunityComment, Long> {
    List<CommunityComment> findByCommunity(Community community);
}
