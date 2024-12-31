package com.example.grupo2backend.Repositories;

import com.example.grupo2backend.Entities.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
//extensión mongoRepository para proporcionar operaciones CRUD para la entidad Cliente
public interface ClienteRepository extends MongoRepository<Cliente, String> {
    Cliente findByEmail(String email);
}
