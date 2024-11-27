package com.korit.projectrrs.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
    private LocalDateTime customerSupportCreateAt;

    @Column(nullable = false)
    private char customerSupportCategory;

    @OneToMany(mappedBy = "customerSupport", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // 순환 참조 방지
    private List<CustomerSupportAttachment> attachments;
}
