package com.petadopt.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_preferences")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "preferred_pet_type", length = 20)
    private String preferredPetType;

    @Column(name = "preferred_age", length = 20)
    private String preferredAge;

    @Column(name = "preferred_size", length = 20)
    private String preferredSize;

    @Column(name = "activity_level", length = 20)
    private String activityLevel;

    @Column(name = "has_children")
    private Boolean hasChildren;

    @Column(name = "has_other_pets")
    private Boolean hasOtherPets;

    @Column(name = "living_space", length = 20)
    private String livingSpace;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}