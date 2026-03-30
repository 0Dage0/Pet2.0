package com.petadopt.controller;

import com.petadopt.dto.ApiResponse;
import com.petadopt.dto.PetDTO;
import com.petadopt.entity.User;
import com.petadopt.security.UserPrincipal;
import com.petadopt.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<ApiResponse> getPets(
            @RequestParam(required = false) String species,
            @RequestParam(required = false) String breed,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String age,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        Long userId = userPrincipal != null ? userPrincipal.getId() : null;

        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<PetDTO> pets = petService.getPetsByFilter(species, breed, gender, age, pageable, userId);

        Map<String, Object> response = new HashMap<>();
        response.put("content", pets.getContent());
        response.put("totalPages", pets.getTotalPages());
        response.put("totalElements", pets.getTotalElements());
        response.put("currentPage", page);
        response.put("pageSize", size);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getPetById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        Long userId = userPrincipal != null ? userPrincipal.getId() : null;
        PetDTO pet = petService.getPetById(id, userId);
        return ResponseEntity.ok(ApiResponse.success(pet));
    }

    @GetMapping("/featured")
    public ResponseEntity<ApiResponse> getFeaturedPets(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Long userId = userPrincipal != null ? userPrincipal.getId() : null;
        List<PetDTO> pets = petService.getFeaturedPets(userId);
        return ResponseEntity.ok(ApiResponse.success(pets));
    }

    @GetMapping("/recommended")
    public ResponseEntity<ApiResponse> getRecommendedPets(
            @RequestParam(required = false) String species,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        Long userId = userPrincipal != null ? userPrincipal.getId() : null;
        List<PetDTO> pets = petService.getRecommendedPets(userId, species);
        return ResponseEntity.ok(ApiResponse.success(pets));
    }

    @GetMapping("/species")
    public ResponseEntity<ApiResponse> getSpeciesList() {
        List<String> species = List.of("dog", "cat", "rabbit");
        return ResponseEntity.ok(ApiResponse.success(species));
    }
}