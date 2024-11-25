
package com.korit.projectrrs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "USER_ID", nullable = false, unique = true)
    private String userId;

    @Column(name = "USER_PASSWORD", nullable = false)
    private String userPassword;

    @Column(name = "USER_NICK_NAME", nullable = false, unique = true)
    private String userNickName;

    @Column(name = "USER_PHONE", nullable = false, unique = true)
    private String userPhone;

    @Column(name = "USER_ADDRESS", nullable = false)
    private String userAddress;

    @Column(name = "USER_ADDRESS_DETAIL", nullable = false)
    private String userAddressDetail;

    @Column(name = "USER_EMAIL", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "USER_PROFILE_IMAGE_URL", nullable = false, columnDefinition = "VARCHAR(50) DEFAULT 'example.jpg'")
    private String userProfileImageUrl;
}