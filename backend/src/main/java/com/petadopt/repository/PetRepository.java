package com.petadopt.repository;

import com.petadopt.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Page<Pet> findByStatus(String status, Pageable pageable);

    Page<Pet> findBySpeciesAndStatus(String species, String status, Pageable pageable);

    Page<Pet> findByShelterIdAndStatus(Long shelterId, String status, Pageable pageable);

    List<Pet> findByIsFeaturedTrueAndStatus(String status);

    @Query("SELECT p FROM Pet p WHERE p.status = :status AND p.isFeatured = true")
    List<Pet> findFeaturedPets(@Param("status") String status);

    @Query("SELECT p FROM Pet p WHERE p.status = :status AND p.species = :species")
    List<Pet> findBySpeciesAndStatusList(@Param("species") String species, @Param("status") String status);

    @Query("SELECT p FROM Pet p WHERE p.status = 'available' " +
           "AND (:species IS NULL OR p.species = :species) " +
           "AND (:breed IS NULL OR p.breed LIKE %:breed%) " +
           "AND (:gender IS NULL OR p.gender = :gender) " +
           "AND (:age IS NULL OR p.age = :age)")
    Page<Pet> findByFilters(
            @Param("species") String species,
            @Param("breed") String breed,
            @Param("gender") String gender,
            @Param("age") String age,
            Pageable pageable);
}