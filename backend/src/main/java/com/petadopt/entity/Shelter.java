package com.petadopt.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "shelters")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "logo_url", length = 255)
    private String logoUrl;

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    private String email;

    @Column(length = 255)
    private String address;

    @Column(length = 50)
    private String city;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    private List<Pet> pets;

    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    private List<Conversation> conversations;
}