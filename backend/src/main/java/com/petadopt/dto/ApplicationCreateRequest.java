package com.petadopt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationCreateRequest {
    private Long petId;
    private String name;
    private String email;
    private String phone;
    private String city;
    private String residenceType;
    private Boolean hasYard;
    private Boolean hasOtherPets;
    private String referrerName;
    private String referrerPhone;
}