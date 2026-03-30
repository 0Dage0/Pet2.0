package com.petadopt.service;

import com.petadopt.dto.ConversationCreateRequest;
import com.petadopt.dto.ConversationDTO;
import com.petadopt.dto.MessageDTO;
import com.petadopt.dto.MessageSendRequest;
import com.petadopt.entity.Conversation;
import com.petadopt.entity.Message;
import com.petadopt.entity.Pet;
import com.petadopt.entity.Shelter;
import com.petadopt.repository.ConversationRepository;
import com.petadopt.repository.MessageRepository;
import com.petadopt.repository.PetRepository;
import com.petadopt.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private PetRepository petRepository;

    public Page<ConversationDTO> getUserConversations(Long userId, Pageable pageable) {
        Page<Conversation> conversations = conversationRepository.findByUserId(userId, pageable);
        return conversations.map(this::toDTO);
    }

    public Page<ConversationDTO> getShelterConversations(Long shelterId, Pageable pageable) {
        Page<Conversation> conversations = conversationRepository.findByShelterId(shelterId, pageable);
        return conversations.map(this::toDTO);
    }

    public ConversationDTO getConversationById(Long id, Long userId) {
        Conversation conv = conversationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conversation not found"));
        return toDTO(conv);
    }

    @Transactional
    public ConversationDTO createConversation(Long userId, ConversationCreateRequest request) {
        // Check if conversation already exists
        var existing = conversationRepository.findByUserIdAndShelterIdAndPetId(
                userId, request.getShelterId(), request.getPetId());

        if (existing.isPresent()) {
            // Send message to existing conversation
            Message message = Message.builder()
                    .conversationId(existing.get().getId())
                    .senderId(userId)
                    .senderType("user")
                    .content(request.getContent())
                    .isRead(false)
                    .build();
            messageRepository.save(message);

            // Update last message time
            existing.get().setLastMessageAt(LocalDateTime.now());
            conversationRepository.save(existing.get());

            return toDTO(existing.get());
        }

        // Create new conversation
        Conversation conversation = Conversation.builder()
                .userId(userId)
                .shelterId(request.getShelterId())
                .petId(request.getPetId())
                .lastMessageAt(LocalDateTime.now())
                .build();

        conversation = conversationRepository.save(conversation);

        // Add first message
        Message message = Message.builder()
                .conversationId(conversation.getId())
                .senderId(userId)
                .senderType("user")
                .content(request.getContent())
                .isRead(false)
                .build();
        messageRepository.save(message);

        return toDTO(conversation);
    }

    public Page<MessageDTO> getConversationMessages(Long conversationId, Pageable pageable, Long currentUserId) {
        Page<Message> messages = messageRepository.findByConversationIdOrderByCreatedAtDesc(conversationId, pageable);
        return messages.map(msg -> toDTO(msg, currentUserId));
    }

    @Transactional
    public MessageDTO sendMessage(Long conversationId, Long senderId, String senderType, MessageSendRequest request) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new RuntimeException("Conversation not found"));

        Message message = Message.builder()
                .conversationId(conversationId)
                .senderId(senderId)
                .senderType(senderType)
                .content(request.getContent())
                .isRead(false)
                .build();

        message = messageRepository.save(message);

        // Update last message time
        conversation.setLastMessageAt(LocalDateTime.now());
        conversationRepository.save(conversation);

        return toDTO(message, senderId);
    }

    @Transactional
    public void markMessagesAsRead(Long conversationId, Long userId) {
        // Mark messages from the other party as read
        Page<Message> messages = messageRepository.findByConversationIdOrderByCreatedAtDesc(conversationId, Pageable.unpaged());
        for (Message msg : messages.getContent()) {
            if (!msg.getIsRead() && !msg.getSenderId().equals(userId)) {
                msg.setIsRead(true);
                messageRepository.save(msg);
            }
        }
    }

    public ConversationDTO toDTO(Conversation conv) {
        String username = null;
        String shelterName = null;
        String petName = null;
        Long unreadCount = 0L;

        // Get shelter name
        Shelter shelter = shelterRepository.findById(conv.getShelterId()).orElse(null);
        if (shelter != null) {
            shelterName = shelter.getName();
        }

        // Get pet name
        if (conv.getPetId() != null) {
            Pet pet = petRepository.findById(conv.getPetId()).orElse(null);
            if (pet != null) {
                petName = pet.getName();
            }
        }

        // Get unread count
        unreadCount = messageRepository.countByConversationIdAndIsReadFalse(conv.getId());

        return ConversationDTO.builder()
                .id(conv.getId())
                .userId(conv.getUserId())
                .username(username)
                .shelterId(conv.getShelterId())
                .shelterName(shelterName)
                .petId(conv.getPetId())
                .petName(petName)
                .lastMessageAt(conv.getLastMessageAt())
                .unreadCount(unreadCount)
                .build();
    }

    public MessageDTO toDTO(Message msg, Long currentUserId) {
        return MessageDTO.builder()
                .id(msg.getId())
                .conversationId(msg.getConversationId())
                .senderId(msg.getSenderId())
                .senderType(msg.getSenderType())
                .content(msg.getContent())
                .isRead(msg.getIsRead())
                .createdAt(msg.getCreatedAt())
                .build();
    }
}