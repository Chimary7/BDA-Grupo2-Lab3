package com.example.grupo2backend.Repositories;

import com.example.grupo2backend.Entities.Entrega;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends MongoRepository<Entrega, String> {
}
