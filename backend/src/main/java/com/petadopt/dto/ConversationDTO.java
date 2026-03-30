package com.petadopt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversationDTO {
    private Long id;
    private Long userId;
    private String username;
    private Long shelterId;
    private String shelterName;
    private Long petId;
    private String petName;
    private LocalDateTime lastMessageAt;
    private Long unreadCount;
}