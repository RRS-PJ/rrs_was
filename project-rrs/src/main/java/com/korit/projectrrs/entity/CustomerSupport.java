package com.korit.projectrrs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "CUSTOMER_SUPPORTS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSupport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerSupportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID", nullable = false)
    private User user;

    @Column(nullable = false)
    private String customerSupportTitle;

    @Column(nullable = false)
    private String customerSupportContent;

    @Column(nullable = false)
    private char customerSupportStatus;

    @Column(nullable = false)
    private LocalDate customerSupportCreateAt;

    @Column(nullable = false)
    private char customerSupportCategory;
}
