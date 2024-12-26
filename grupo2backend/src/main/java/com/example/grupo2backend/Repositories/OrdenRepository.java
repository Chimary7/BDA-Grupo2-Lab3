package com.example.grupo2backend.Repositories;

import com.example.grupo2backend.Entities.Orden;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenRepository extends MongoRepository<Orden, String> {
    List<Orden> findByIdCliente(String idCliente);
}
