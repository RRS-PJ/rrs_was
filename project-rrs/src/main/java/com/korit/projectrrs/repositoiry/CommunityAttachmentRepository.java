package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.CommunityAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityAttachmentRepository extends JpaRepository<CommunityAttachment, Long> {

    List<CommunityAttachment> findByCommunityCommunityId(Long communityId);
}
