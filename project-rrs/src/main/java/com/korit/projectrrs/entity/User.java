
package com.korit.projectrrs.entity;

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
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String userName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

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