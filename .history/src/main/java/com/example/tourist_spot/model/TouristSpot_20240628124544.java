package com.example.tourist_spot.model;

// Importa a classe LocalDateTime para trabalhar com data e hora
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Indica que esta classe é uma entidade JPA
public class TouristSpot {

    @Id // Indica que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define a estratégia de geração automática do ID

     // Campos da entidade TouristSpot
    private Long id;
    private String name;
    private String description;
    private String location;
    private String city;
    private String state;
    private LocalDateTime dateAdded;

     // Método getter e setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public LocalDateTime getDateAdded() {
        return dateAdded;
    }
    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    

}
