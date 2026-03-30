package com.petadopt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {
    private Long id;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String age;
    private String ageDetail;
    private BigDecimal weight;
    private String description;
    private String temperament;
    private String healthStatus;
    private Boolean vaccinated;
    private Boolean neutered;
    private Boolean microchipped;
    private List<String> images;
    private String videoUrl;
    private Long shelterId;
    private String shelterName;
    private String status;
    private Boolean isFeatured;
    private String category;
    private LocalDateTime createdAt;
    private Boolean isFavorited;
}