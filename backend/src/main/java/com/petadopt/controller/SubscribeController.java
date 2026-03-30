package com.petadopt.controller;

import com.petadopt.dto.ApiResponse;
import com.petadopt.dto.SubscribeRequest;
import com.petadopt.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscribe")
public class SubscribeController {

    @Autowired
    private SubscribeService subscribeService;

    @PostMapping
    public ResponseEntity<ApiResponse> subscribe(@RequestBody SubscribeRequest request) {
        try {
            String result = subscribeService.subscribe(request.getEmail());
            return ResponseEntity.ok(ApiResponse.success(result, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}