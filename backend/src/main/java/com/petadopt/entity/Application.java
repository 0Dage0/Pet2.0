package com.petadopt.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pet_id", nullable = false)
    private Long petId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(length = 50)
    private String city;

    @Column(name = "residence_type", length = 20)
    private String residenceType;

    @Column(name = "has_yard")
    private Boolean hasYard;

    @Column(name = "has_other_pets")
    private Boolean hasOtherPets;

    @Column(name = "referrer_name", length = 50)
    private String referrerName;

    @Column(name = "referrer_phone", length = 20)
    private String referrerPhone;

    @Column(length = 20)
    private String status;

    @Column(name = "current_step")
    private Integer currentStep;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) status = "pending";
        if (currentStep == null) currentStep = 1;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", insertable = false, updatable = false)
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}