package com.example.tourist_spot.service.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tourist_spot.model.TouristSpot;
import com.example.tourist_spot.service.TouristSpotService;

@Controller // Indica que esta classe é um controlador Spring MVC
public class TouristSpotController {

    @Autowired // Injeta automaticamente a dependência do TouristSpotService
    private TouristSpotService service;

    // Mapeia requisições GET para a URL raiz ("/")
    @GetMapping("/")
    public String viewHomePage(Model model,
                               @RequestParam(value = "keyword", required = false) String keyword,
                               @PageableDefault(size = 10, sort = "dateAdded", direction = Sort.Direction.DESC) Pageable pageable) {
        // Chama o serviço para listar todos os pontos turísticos com paginação e busca por palavra-chave
        Page<TouristSpot> page = service.listAll(keyword, pageable);
        // Adiciona a página de resultados e a palavra-chave ao modelo
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        // Retorna o nome da visão (template) a ser renderizado
        return "index";
    }

    // Mapeia requisições GET para a URL "/new"
    @GetMapping("/new")
    public String showNewForm(Model model) {
        // Adiciona um novo objeto TouristSpot e a lista de estados ao modelo
        model.addAttribute("spot", new TouristSpot());
        model.addAttribute("states", getStates());
        // Retorna o nome da visão (template) a ser renderizado
        return "new_spot";
    }

    // Mapeia requisições POST para a URL "/save"
    @PostMapping("/save")
    public String saveTouristSpot(@ModelAttribute("spot") TouristSpot spot) {
        // Chama o serviço para salvar o ponto turístico
        service.save(spot);
        // Redireciona para a página inicial
        return "redirect:/";
    }

    // Mapeia requisições GET para a URL "/view/{id}"
    @GetMapping("/view/{id}")
    public String viewSpot(@PathVariable("id") Long id, Model model) {
        // Chama o serviço para obter o ponto turístico pelo ID
        TouristSpot spot = service.get(id);
        // Adiciona o ponto turístico ao modelo
        model.addAttribute("spot", spot);
        // Retorna o nome da visão (template) a ser renderizado
        return "view_spot";
    }
    

    // Método privado para obter a lista de estados brasileiros
    private List<String> getStates() {
        return Arrays.asList("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO");
    }
}
