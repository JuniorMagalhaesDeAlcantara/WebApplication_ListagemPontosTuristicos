package com.example.tourist_spot.repository;

// Importa as classes necessárias do Spring Data
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tourist_spot.model.TouristSpot;

// Interface que estende JpaRepository para realizar operações de banco de dados com a entidade TouristSpot
public interface TouristSpotRepository extends JpaRepository<TouristSpot, Long> {
    
    // Declaração de um método de consulta customizado para encontrar TouristSpot
    // O método busca pontos turísticos cujo nome, descrição ou localização contenham o texto fornecido
    // A busca é paginada, permitindo resultados em páginas
    Page<TouristSpot> findByNameContainingOrDescriptionContainingOrLocationContaining(
        String name, String description, String location, org.springframework.data.domain.Pageable pageable);
}

