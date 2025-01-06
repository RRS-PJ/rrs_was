package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.CommunityLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommunityLikesRepository extends JpaRepository<CommunityLikes, Long> {

    List<CommunityLikes> findByCommunityCommunityIdAndUserLikedTrue(Long communityId);

    Optional<CommunityLikes> findByCommunityCommunityIdAndUserUserId(Long communityId, Long userId);
}
