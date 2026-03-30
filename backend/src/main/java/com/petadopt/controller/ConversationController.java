package com.petadopt.controller;

import com.petadopt.dto.ApiResponse;
import com.petadopt.dto.ConversationCreateRequest;
import com.petadopt.dto.ConversationDTO;
import com.petadopt.dto.MessageDTO;
import com.petadopt.dto.MessageSendRequest;
import com.petadopt.security.UserPrincipal;
import com.petadopt.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @GetMapping
    public ResponseEntity<ApiResponse> getConversations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        if (userPrincipal == null) {
            return ResponseEntity.status(401).build();
        }

        PageRequest pageable = PageRequest.of(page, size);
        Page<ConversationDTO> conversations = conversationService.getUserConversations(userPrincipal.getId(), pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("content", conversations.getContent());
        response.put("totalPages", conversations.getTotalPages());
        response.put("totalElements", conversations.getTotalElements());
        response.put("currentPage", page);
        response.put("pageSize", size);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getConversationById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        if (userPrincipal == null) {
            return ResponseEntity.status(401).build();
        }

        ConversationDTO conversation = conversationService.getConversationById(id, userPrincipal.getId());
        return ResponseEntity.ok(ApiResponse.success(conversation));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createConversation(
            @RequestBody ConversationCreateRequest request,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        if (userPrincipal == null) {
            return ResponseEntity.status(401).build();
        }

        ConversationDTO conversation = conversationService.createConversation(userPrincipal.getId(), request);
        return ResponseEntity.ok(ApiResponse.success("Conversation created", conversation));
    }

    @GetMapping("/{id}/messages")
    public ResponseEntity<ApiResponse> getMessages(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        if (userPrincipal == null) {
            return ResponseEntity.status(401).build();
        }

        PageRequest pageable = PageRequest.of(page, size);
        Page<MessageDTO> messages = conversationService.getConversationMessages(id, pageable, userPrincipal.getId());

        // Mark messages as read
        conversationService.markMessagesAsRead(id, userPrincipal.getId());

        Map<String, Object> response = new HashMap<>();
        response.put("content", messages.getContent());
        response.put("totalPages", messages.getTotalPages());
        response.put("totalElements", messages.getTotalElements());
        response.put("currentPage", page);
        response.put("pageSize", size);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PostMapping("/{id}/messages")
    public ResponseEntity<ApiResponse> sendMessage(
            @PathVariable Long id,
            @RequestBody MessageSendRequest request,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        if (userPrincipal == null) {
            return ResponseEntity.status(401).build();
        }

        MessageDTO message = conversationService.sendMessage(id, userPrincipal.getId(), "user", request);
        return ResponseEntity.ok(ApiResponse.success("Message sent", message));
    }
}