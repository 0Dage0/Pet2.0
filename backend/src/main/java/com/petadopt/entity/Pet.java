package com.petadopt.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 20)
    private String species;

    @Column(length = 50)
    private String breed;

    @Column(length = 10)
    private String gender;

    @Column(length = 20)
    private String age;

    @Column(name = "age_detail", length = 20)
    private String ageDetail;

    @Column(precision = 5, scale = 2)
    private BigDecimal weight;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String temperament;

    @Column(name = "health_status", length = 100)
    private String healthStatus;

    @Column
    private Boolean vaccinated;

    @Column
    private Boolean neutered;

    @Column
    private Boolean microchipped;

    @Column(columnDefinition = "JSON")
    private String images;

    @Column(name = "video_url", length = 255)
    private String videoUrl;

    @Column(name = "shelter_id")
    private Long shelterId;

    @Column(length = 20)
    private String status;

    @Column(name = "is_featured")
    private Boolean isFeatured;

    @Column(length = 20)
    private String category;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) status = "available";
        if (isFeatured == null) isFeatured = false;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelter_id", insertable = false, updatable = false)
    private Shelter shelter;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private List<Application> applications;
}