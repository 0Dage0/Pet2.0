package com.petadopt.controller;

import com.petadopt.dto.ApiResponse;
import com.petadopt.entity.Shelter;
import com.petadopt.service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shelters")
public class ShelterController {

    @Autowired
    private ShelterService shelterService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllShelters() {
        List<Shelter> shelters = shelterService.getAllShelters();
        return ResponseEntity.ok(ApiResponse.success(shelters));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getShelterById(@PathVariable Long id) {
        Shelter shelter = shelterService.getShelterById(id);
        return ResponseEntity.ok(ApiResponse.success(shelter));
    }
}