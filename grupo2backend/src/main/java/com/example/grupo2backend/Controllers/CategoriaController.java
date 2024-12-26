package com.example.grupo2backend.Controllers;

import com.example.grupo2backend.Entities.Categoria;
import com.example.grupo2backend.Repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping("/")
    public Boolean saveCategoria(@RequestBody Categoria categoria) {
        try {
            categoriaRepository.save(categoria);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @GetMapping("/")
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Categoria getCategoriaById(@PathVariable String id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @GetMapping("/nombre/{nombre}")
    public List<Categoria> getCategoriaByNombre(@PathVariable String nombre) {
        return categoriaRepository.findByNombre(nombre);
    }

    @PutMapping("/")
    public Boolean updateCategoria(@RequestBody Categoria categoria) {
        try {
            categoriaRepository.save(categoria);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @DeleteMapping("/{id}")
    public Boolean deleteCategoriaById(@PathVariable String id) {
        try {
            categoriaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
