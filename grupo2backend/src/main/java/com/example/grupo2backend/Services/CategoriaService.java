package com.example.grupo2backend.Services;

import com.example.grupo2backend.Entities.Categoria;
import com.example.grupo2backend.Repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Boolean saveCategoria(Categoria categoria) {
        try {
            categoriaRepository.save(categoria);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Categoria> findAll() {return categoriaRepository.findAll();}

    public Categoria findCategoriaById(String id) {return categoriaRepository.findById(id).orElse(null);}

    public List<Categoria> findCategoriaByNombre(String nombre) {return categoriaRepository.findByNombre(nombre);}

    public Boolean updateCategoria(Categoria categoria){
        try {
            categoriaRepository.save(categoria);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean deleteCategoriaById(String id) {
        try {
            categoriaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
