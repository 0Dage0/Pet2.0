package com.petadopt.repository;

import com.petadopt.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Page<Application> findByUserId(Long userId, Pageable pageable);
    Page<Application> findByPetId(Long petId, Pageable pageable);
    Page<Application> findByStatus(String status, Pageable pageable);
    Optional<Application> findByUserIdAndPetId(Long userId, Long petId);
    boolean existsByUserIdAndPetId(Long userId, Long petId);
}