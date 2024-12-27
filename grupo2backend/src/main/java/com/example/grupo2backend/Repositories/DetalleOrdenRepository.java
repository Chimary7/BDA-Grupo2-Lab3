package com.example.grupo2backend.Repositories;

import com.example.grupo2backend.Entities.DetalleOrden;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DetalleOrdenRepository extends MongoRepository<DetalleOrden, String> {
    List<DetalleOrden> findByIdOrden(String idOrden);
    List<DetalleOrden> findByIdProducto(String idProducto);
}
