package com.petadopt.service;

import com.petadopt.entity.Shelter;
import com.petadopt.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShelterService {

    @Autowired
    private ShelterRepository shelterRepository;

    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    public Shelter getShelterById(Long id) {
        return shelterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shelter not found"));
    }

    public Shelter createShelter(Shelter shelter) {
        return shelterRepository.save(shelter);
    }

    public Shelter updateShelter(Long id, Shelter shelter) {
        Shelter existing = shelterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shelter not found"));

        existing.setName(shelter.getName());
        existing.setDescription(shelter.getDescription());
        existing.setLogoUrl(shelter.getLogoUrl());
        existing.setPhone(shelter.getPhone());
        existing.setEmail(shelter.getEmail());
        existing.setAddress(shelter.getAddress());
        existing.setCity(shelter.getCity());

        return shelterRepository.save(existing);
    }
}