package com.example.grupo2backend.Repositories;

import com.example.grupo2backend.Entities.Valoracion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValoracionRepository extends MongoRepository<Valoracion, String> {
    List<Valoracion> findByIdProducto(String idProducto);
}
