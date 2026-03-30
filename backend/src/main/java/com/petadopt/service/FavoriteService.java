package com.petadopt.service;

import com.petadopt.dto.FavoriteDTO;
import com.petadopt.dto.PetDTO;
import com.petadopt.entity.Favorite;
import com.petadopt.entity.Pet;
import com.petadopt.entity.Shelter;
import com.petadopt.repository.FavoriteRepository;
import com.petadopt.repository.PetRepository;
import com.petadopt.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private PetService petService;

    public Page<FavoriteDTO> getUserFavorites(Long userId, Pageable pageable) {
        Page<Favorite> favorites = favoriteRepository.findByUserId(userId, pageable);
        return favorites.map(fav -> {
            Pet pet = petRepository.findById(fav.getPetId()).orElse(null);
            return FavoriteDTO.builder()
                    .id(fav.getId())
                    .petId(fav.getPetId())
                    .pet(pet != null ? petService.toDTO(pet, userId) : null)
                    .build();
        });
    }

    @Transactional
    public FavoriteDTO addFavorite(Long userId, Long petId) {
        if (favoriteRepository.existsByUserIdAndPetId(userId, petId)) {
            throw new RuntimeException("Pet already favorited");
        }

        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        Favorite favorite = Favorite.builder()
                .userId(userId)
                .petId(petId)
                .build();

        favorite = favoriteRepository.save(favorite);

        return FavoriteDTO.builder()
                .id(favorite.getId())
                .petId(petId)
                .pet(petService.toDTO(pet, userId))
                .build();
    }

    @Transactional
    public void removeFavorite(Long userId, Long petId) {
        Optional<Favorite> favorite = favoriteRepository.findByUserIdAndPetId(userId, petId);
        if (favorite.isPresent()) {
            favoriteRepository.delete(favorite.get());
        } else {
            throw new RuntimeException("Favorite not found");
        }
    }

    public boolean isFavorited(Long userId, Long petId) {
        return favoriteRepository.existsByUserIdAndPetId(userId, petId);
    }
}