package com.example.grupo2backend.Services;

import com.example.grupo2backend.Entities.ArchivosProducto;
import com.example.grupo2backend.Repositories.ArchivosProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchivosProductosService {
    @Autowired
    private ArchivosProductoRepository archivosProductoRepository;

    public Boolean saveArchivoProducto(ArchivosProducto archivoProducto) {
        try {
            archivosProductoRepository.save(archivoProducto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<ArchivosProducto> findAll() {
        return archivosProductoRepository.findAll();
    }

    public ArchivosProducto findArchivoProductoById(String id) {
        return archivosProductoRepository.findById(id).orElse(null);
    }

    public List<ArchivosProducto> findArchivoProductoByIdProducto(String idProducto) {
        return archivosProductoRepository.findByIdProducto(idProducto);
    }

    public List<ArchivosProducto> findArchivoProductoByIdProductoAndTipo(String idProducto, String tipo) {
        return archivosProductoRepository.findByIdProductoAndTipo(idProducto, tipo);
    }

    public Boolean updateArchivoProducto(ArchivosProducto archivoProducto) {
        try {
            archivosProductoRepository.save(archivoProducto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean deleteArchivoProductoById(String id) {
        try {
            archivosProductoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
