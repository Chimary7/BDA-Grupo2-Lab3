package com.example.grupo2backend.Repositories;

import com.example.grupo2backend.Entities.ArchivosProducto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchivosProductoRepository extends MongoRepository<ArchivosProducto, String> {
    List<ArchivosProducto> findByIdProducto(String idProducto);
    List<ArchivosProducto> findByIdProductoAndTipo(String idProducto, String tipo);
}
