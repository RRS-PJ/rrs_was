package com.korit.projectrrs.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @JsonIgnoreProperties
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NICKNAME", nullable = false, unique = true)
    private String nickname;

    @Column(name = "PHONE", nullable = false, unique = true)
    private String phone;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "ADDRESS_DETAIL", nullable = false)
    private String addressDetail;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PROFILE_IMAGE_URL")
    private String profileImageUrl;

    @Column(name = "ROLES")
    private String roles;

    @Column(name = "PROVIDER_INTRODUCTION", columnDefinition = "TEXT")
    private String providerIntroduction;
}