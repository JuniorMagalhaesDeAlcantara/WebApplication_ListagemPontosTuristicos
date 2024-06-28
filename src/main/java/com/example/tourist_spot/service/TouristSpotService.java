package com.example.tourist_spot.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.example.tourist_spot.model.TouristSpot;
import com.example.tourist_spot.repository.TouristSpotRepository;

@Service // Indica que esta classe é um serviço do Spring

public class TouristSpotService {
    @Autowired // Injeta automaticamente a dependência do TouristSpotRepository
    
    private TouristSpotRepository repository;

    // Método para listar todos os pontos turísticos, com suporte a busca por palavra-chave e paginação
    public Page<TouristSpot> listAll(String keyword, Pageable pageable) {
         // Se a palavra-chave for fornecida, realiza uma busca pelos campos name, description ou location
        if (keyword != null) {
            return repository.findByNameContainingOrDescriptionContainingOrLocationContaining(keyword, keyword, keyword, pageable);
        }
        // Caso contrário, retorna todos os pontos turísticos paginados
        return repository.findAll(pageable);
    }

    // Método para salvar um ponto turístico no repositório
    public void save(TouristSpot spot) {
        // Define a data atual como a data em que o ponto turístico foi adicionado
        spot.setDateAdded(LocalDateTime.now());
        // Salva o ponto turístico no repositório
        repository.save(spot);
    }

    // Método para obter um ponto turístico pelo seu ID
    public TouristSpot get(Long id) {
        // Busca o ponto turístico pelo ID, lançando uma exceção se não for encontrado
        return repository.findById(id).orElseThrow(() -> new ResourceAccessException("Spot not found"));
    }
}
