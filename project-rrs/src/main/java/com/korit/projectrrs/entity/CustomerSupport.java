package com.korit.projectrrs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "CUSTOMER_SUPPORTS")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSupport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_SUPPORT_ID", nullable = false)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "CUSTOMER_SUPPORT_TITLE", nullable = false)
    private String customerSupportTitle;

    @Column(name = "CUSTOMER_SUPPORT_CONTENT",nullable = false)
    private String customerSupportContent;

    @Column(name = "CUSTOMER_SUPPORT_STATUS", nullable = false)
    private char customerSupportStatus; // 0: 미처리, 1: 처리완료

    @Column(name = "CUSTOMER_SUPPORT_CREATE_AT", nullable = false)
    private LocalDateTime customerSupportCreateAt;

    @Column(name= "CUSTOMER_SUPPORT_CATEGORY", nullable = false)
    private char customerSupportCategory; // '0: 신고 / 1:문의'
}
