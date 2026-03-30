package com.petadopt.repository;

import com.petadopt.entity.Conversation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    Page<Conversation> findByUserId(Long userId, Pageable pageable);
    Page<Conversation> findByShelterId(Long shelterId, Pageable pageable);
    Optional<Conversation> findByUserIdAndShelterIdAndPetId(Long userId, Long shelterId, Long petId);
}