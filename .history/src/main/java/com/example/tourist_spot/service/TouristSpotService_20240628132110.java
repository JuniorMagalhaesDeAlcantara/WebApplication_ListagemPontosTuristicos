package com.example.tourist_spot.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.example.tourist_spot.model.TouristSpot;
import com.example.tourist_spot.repository.TouristSpotRepository;

@Service
public class TouristSpotService {
    @Autowired
    private TouristSpotRepository repository;

    public Page<TouristSpot> listAll(String keyword, Pageable pageable) {
        if (keyword != null) {
            return repository.findByNameContainingOrDescriptionContainingOrLocationContaining(keyword, keyword, keyword, pageable);
        }
        return repository.findAll(pageable);
    }

    public void save(TouristSpot spot) {
        spot.setDateAdded(LocalDateTime.now());
        repository.save(spot);
    }

    public TouristSpot get(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceAccessException("Spot not found"));
    }
}
