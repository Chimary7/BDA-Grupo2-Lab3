package com.example.grupo2backend.Repositories;

import com.example.grupo2backend.Entities.Categoria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends MongoRepository<Categoria, String> {
    List<Categoria> findByNombre(String nombre);
}
