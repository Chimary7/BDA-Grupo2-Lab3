package com.example.grupo2backend.Repositories;

import com.example.grupo2backend.Entities.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends MongoRepository<Producto, String> {
    List<Producto> findByIdCategoria(String idCategoria);

}
