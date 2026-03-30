package com.petadopt.controller;

import com.petadopt.dto.ApiResponse;
import com.petadopt.dto.FavoriteDTO;
import com.petadopt.security.UserPrincipal;
import com.petadopt.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping
    public ResponseEntity<ApiResponse> getFavorites(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        if (userPrincipal == null) {
            return ResponseEntity.status(401).build();
        }

        PageRequest pageable = PageRequest.of(page, size);
        Page<FavoriteDTO> favorites = favoriteService.getUserFavorites(userPrincipal.getId(), pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("content", favorites.getContent());
        response.put("totalPages", favorites.getTotalPages());
        response.put("totalElements", favorites.getTotalElements());
        response.put("currentPage", page);
        response.put("pageSize", size);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addFavorite(
            @RequestParam Long petId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        if (userPrincipal == null) {
            return ResponseEntity.status(401).build();
        }

        FavoriteDTO favorite = favoriteService.addFavorite(userPrincipal.getId(), petId);
        return ResponseEntity.ok(ApiResponse.success("Added to favorites", favorite));
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<ApiResponse> removeFavorite(
            @PathVariable Long petId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        if (userPrincipal == null) {
            return ResponseEntity.status(401).build();
        }

        favoriteService.removeFavorite(userPrincipal.getId(), petId);
        return ResponseEntity.ok(ApiResponse.success("Removed from favorites", null));
    }
}