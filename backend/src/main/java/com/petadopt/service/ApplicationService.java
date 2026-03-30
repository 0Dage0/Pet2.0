package com.petadopt.service;

import com.petadopt.dto.ApplicationCreateRequest;
import com.petadopt.dto.ApplicationDTO;
import com.petadopt.entity.Application;
import com.petadopt.entity.Pet;
import com.petadopt.repository.ApplicationRepository;
import com.petadopt.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private PetRepository petRepository;

    public Page<ApplicationDTO> getUserApplications(Long userId, Pageable pageable) {
        Page<Application> applications = applicationRepository.findByUserId(userId, pageable);
        return applications.map(this::toDTO);
    }

    public ApplicationDTO getApplicationById(Long id) {
        Application app = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        return toDTO(app);
    }

    @Transactional
    public ApplicationDTO createApplication(Long userId, ApplicationCreateRequest request) {
        if (applicationRepository.existsByUserIdAndPetId(userId, request.getPetId())) {
            throw new RuntimeException("You have already applied for this pet");
        }

        Pet pet = petRepository.findById(request.getPetId())
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        Application application = Application.builder()
                .petId(request.getPetId())
                .userId(userId)
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .city(request.getCity())
                .residenceType(request.getResidenceType())
                .hasYard(request.getHasYard())
                .hasOtherPets(request.getHasOtherPets())
                .referrerName(request.getReferrerName())
                .referrerPhone(request.getReferrerPhone())
                .status("pending")
                .currentStep(1)
                .build();

        application = applicationRepository.save(application);

        return toDTO(application);
    }

    @Transactional
    public ApplicationDTO updateApplication(Long id, Application application) {
        Application existing = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (application.getStatus() != null) {
            existing.setStatus(application.getStatus());
        }
        if (application.getCurrentStep() != null) {
            existing.setCurrentStep(application.getCurrentStep());
        }

        existing = applicationRepository.save(existing);
        return toDTO(existing);
    }

    public ApplicationDTO toDTO(Application app) {
        String petName = null;
        String petImage = null;

        Pet pet = petRepository.findById(app.getPetId()).orElse(null);
        if (pet != null) {
            petName = pet.getName();
            if (pet.getImages() != null && pet.getImages().contains("[")) {
                try {
                    String firstImage = pet.getImages().split("\"")[1];
                    petImage = firstImage;
                } catch (Exception e) {
                    petImage = null;
                }
            }
        }

        return ApplicationDTO.builder()
                .id(app.getId())
                .petId(app.getPetId())
                .petName(petName)
                .petImage(petImage)
                .userId(app.getUserId())
                .name(app.getName())
                .email(app.getEmail())
                .phone(app.getPhone())
                .city(app.getCity())
                .residenceType(app.getResidenceType())
                .hasYard(app.getHasYard())
                .hasOtherPets(app.getHasOtherPets())
                .referrerName(app.getReferrerName())
                .referrerPhone(app.getReferrerPhone())
                .status(app.getStatus())
                .currentStep(app.getCurrentStep())
                .createdAt(app.getCreatedAt())
                .build();
    }
}