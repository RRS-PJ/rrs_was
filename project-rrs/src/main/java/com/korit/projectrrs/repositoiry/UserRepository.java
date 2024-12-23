package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Modifying;
=======
>>>>>>> develop
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);

<<<<<<< HEAD
//    @Modifying
//    @Query("""
//        UPDATE User u
//        SET u.roles = :roles,
//        u.providerIntroduction = null
//        WHERE u.userId = :userId
//        """)
//    void updateRolesAndproviderIntroductionByUserId(@Param("userId") Long userId, @Param("roles") String roles, @Param("providerIntroduction") String providerIntroduction);
=======
    @Query(value = """
SELECT
    *
FROM
    USERS U
WHERE
    U.USER_ID = :userId
    AND U.ROLES LIKE '%ROLE_PROVIDER%';
""",nativeQuery = true)
    Optional<User> findProviderById(@Param("userId") Long providerId);
>>>>>>> develop
}