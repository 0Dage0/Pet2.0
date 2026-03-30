package com.petadopt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {
    private Long id;
    private Long petId;
    private String petName;
    private String petImage;
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String city;
    private String residenceType;
    private Boolean hasYard;
    private Boolean hasOtherPets;
    private String referrerName;
    private String referrerPhone;
    private String status;
    private Integer currentStep;
    private LocalDateTime createdAt;
}