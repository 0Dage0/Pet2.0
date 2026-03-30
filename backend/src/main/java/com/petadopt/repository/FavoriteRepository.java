package com.petadopt.repository;

import com.petadopt.entity.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Page<Favorite> findByUserId(Long userId, Pageable pageable);
    Optional<Favorite> findByUserIdAndPetId(Long userId, Long petId);
    boolean existsByUserIdAndPetId(Long userId, Long petId);
    void deleteByUserIdAndPetId(Long userId, Long petId);
}