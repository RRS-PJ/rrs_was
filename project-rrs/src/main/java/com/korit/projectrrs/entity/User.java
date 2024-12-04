
package com.korit.projectrrs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "USERNAME", nullable = false, length = 50, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false, length = 50)
    private String password;

    @Column(name = "NICKNAME", nullable = false, length = 50, unique = true)
    private String nickname;

    @Column(name = "PHONE", nullable = false, length = 20, unique = true)
    private String phone;

    @Column(name = "ADDRESS", nullable = false, length = 255)
    private String address;

    @Column(name = "ADDRESS_DETAIL", nullable = false, length = 255)
    private String addressDetail;

    @Column(name = "EMAIL", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "PROFILE_IMAGE_URL", length = 255)
    private String profileImageUrl;

    @Column(name = "ROLE", length = 255)
    private String role;

    @Column(name = "PROVIDER_INTRODUCTION", columnDefinition = "TEXT")
    private String providerIntroduction;
}