package com.korit.projectrrs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false, unique = true)
    private String userNickName;

    @Column(nullable = false, unique = true)
    private String userPhone;

    @Column(nullable = false)
    private String userAddress;

    @Column(nullable = false)
    private String userAddressDetail;

    @Column(nullable = false, unique = true)
    private String userEmail;

    private String userProfileImageUrl;
}
