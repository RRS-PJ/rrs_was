package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.CommunityAttachment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityAttachmentRepository extends JpaRepository<CommunityAttachment, Long> {
    List<CommunityAttachment> findByCommunityCommunityId(Long communityId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM COMMUNITY_ATTACHMENTS WHERE COMMUNITY_ID = :communityId", nativeQuery = true)
    void deleteAll(@Param("communityId") Long communityId);
}
