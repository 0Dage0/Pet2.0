package com.petadopt.controller;

import com.petadopt.dto.ApiResponse;
import com.petadopt.dto.ApplicationCreateRequest;
import com.petadopt.dto.ApplicationDTO;
import com.petadopt.security.UserPrincipal;
import com.petadopt.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public ResponseEntity<ApiResponse> getApplications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        if (userPrincipal == null) {
            return ResponseEntity.status(401).build();
        }

        PageRequest pageable = PageRequest.of(page, size);
        Page<ApplicationDTO> applications = applicationService.getUserApplications(userPrincipal.getId(), pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("content", applications.getContent());
        response.put("totalPages", applications.getTotalPages());
        response.put("totalElements", applications.getTotalElements());
        response.put("currentPage", page);
        response.put("pageSize", size);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getApplicationById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        if (userPrincipal == null) {
            return ResponseEntity.status(401).build();
        }

        ApplicationDTO application = applicationService.getApplicationById(id);
        return ResponseEntity.ok(ApiResponse.success(application));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createApplication(
            @RequestBody ApplicationCreateRequest request,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        if (userPrincipal == null) {
            return ResponseEntity.status(401).build();
        }

        ApplicationDTO application = applicationService.createApplication(userPrincipal.getId(), request);
        return ResponseEntity.ok(ApiResponse.success("Application submitted successfully", application));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateApplication(
            @PathVariable Long id,
            @RequestBody ApplicationDTO request,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        if (userPrincipal == null) {
            return ResponseEntity.status(401).build();
        }

        ApplicationDTO application = applicationService.updateApplication(id,
            com.petadopt.entity.Application.builder()
                .status(request.getStatus())
                .currentStep(request.getCurrentStep())
                .build());
        return ResponseEntity.ok(ApiResponse.success("Application updated", application));
    }
}