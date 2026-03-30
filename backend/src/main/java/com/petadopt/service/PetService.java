package com.petadopt.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petadopt.dto.PetDTO;
import com.petadopt.entity.Pet;
import com.petadopt.entity.Shelter;
import com.petadopt.repository.FavoriteRepository;
import com.petadopt.repository.PetRepository;
import com.petadopt.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Page<PetDTO> getAllPets(Pageable pageable, Long userId) {
        Page<Pet> pets = petRepository.findByStatus("available", pageable);
        return pets.map(pet -> toDTO(pet, userId));
    }

    public Page<PetDTO> getPetsBySpecies(String species, Pageable pageable, Long userId) {
        Page<Pet> pets = petRepository.findBySpeciesAndStatus(species, "available", pageable);
        return pets.map(pet -> toDTO(pet, userId));
    }

    public Page<PetDTO> getPetsByFilter(String species, String breed, String gender, String age, Pageable pageable, Long userId) {
        Page<Pet> pets = petRepository.findByFilters(species, breed, gender, age, pageable);
        return pets.map(pet -> toDTO(pet, userId));
    }

    public PetDTO getPetById(Long id, Long userId) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        return toDTO(pet, userId);
    }

    public List<PetDTO> getFeaturedPets(Long userId) {
        List<Pet> pets = petRepository.findFeaturedPets("available");
        return pets.stream()
                .map(pet -> toDTO(pet, userId))
                .collect(Collectors.toList());
    }

    public List<PetDTO> getRecommendedPets(Long userId, String preferredSpecies) {
        List<Pet> pets;
        if (preferredSpecies != null) {
            pets = petRepository.findBySpeciesAndStatusList(preferredSpecies, "available");
        } else {
            pets = petRepository.findFeaturedPets("available");
        }

        // Limit to 8 pets
        return pets.stream()
                .limit(8)
                .map(pet -> toDTO(pet, userId))
                .collect(Collectors.toList());
    }

    public PetDTO toDTO(Pet pet, Long userId) {
        String shelterName = null;
        if (pet.getShelterId() != null) {
            Shelter shelter = shelterRepository.findById(pet.getShelterId()).orElse(null);
            if (shelter != null) {
                shelterName = shelter.getName();
            }
        }

        List<String> images = new ArrayList<>();
        if (pet.getImages() != null) {
            try {
                images = objectMapper.readValue(pet.getImages(), new TypeReference<List<String>>() {});
            } catch (Exception e) {
                images = new ArrayList<>();
            }
        }

        Boolean isFavorited = false;
        if (userId != null) {
            isFavorited = favoriteRepository.existsByUserIdAndPetId(userId, pet.getId());
        }

        return PetDTO.builder()
                .id(pet.getId())
                .name(pet.getName())
                .species(pet.getSpecies())
                .breed(pet.getBreed())
                .gender(pet.getGender())
                .age(pet.getAge())
                .ageDetail(pet.getAgeDetail())
                .weight(pet.getWeight())
                .description(pet.getDescription())
                .temperament(pet.getTemperament())
                .healthStatus(pet.getHealthStatus())
                .vaccinated(pet.getVaccinated())
                .neutered(pet.getNeutered())
                .microchipped(pet.getMicrochipped())
                .images(images)
                .videoUrl(pet.getVideoUrl())
                .shelterId(pet.getShelterId())
                .shelterName(shelterName)
                .status(pet.getStatus())
                .isFeatured(pet.getIsFeatured())
                .category(pet.getCategory())
                .createdAt(pet.getCreatedAt())
                .isFavorited(isFavorited)
                .build();
    }
}