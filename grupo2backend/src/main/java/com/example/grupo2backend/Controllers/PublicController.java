package com.example.grupo2backend.Controllers;

import com.example.grupo2backend.Entities.Categoria;
import com.example.grupo2backend.Repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/categorias")
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

}
