package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);

    boolean existsByUserId(String userId);// userId정보가 있는지 여부 확인
    boolean existsByUserNickName(String userNickName);// userNickName정보가 있는지 여부 확인
    boolean existsByUserPhone(String userphone); // userPhone정보가 있는지 여부 확인
    boolean existsByUserEmail(String email);// userEmail정보가 있는지 여부 확인

}