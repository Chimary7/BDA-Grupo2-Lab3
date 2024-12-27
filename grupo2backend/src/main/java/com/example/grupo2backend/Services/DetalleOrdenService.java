package com.example.grupo2backend.Services;

import com.example.grupo2backend.Entities.DetalleOrden;
import com.example.grupo2backend.Repositories.DetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleOrdenService {
    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;

    public Boolean saveDetalleOrden(DetalleOrden detalleOrden) {
        try{
            detalleOrdenRepository.save(detalleOrden);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public List<DetalleOrden> findAll() {
        return detalleOrdenRepository.findAll();
    }

    public DetalleOrden findDetalleOrdenById(String id) {
        return detalleOrdenRepository.findById(id).orElse(null);
    }

    public List<DetalleOrden> findDetalleOrdenByIdOrden(String idOrden) {
        return detalleOrdenRepository.findByIdOrden(idOrden);
    }

    public List<DetalleOrden> findDetalleOrdenByIdProducto(String idProducto) {
        return detalleOrdenRepository.findByIdProducto(idProducto);
    }

    public Boolean updateDetalleOrden(DetalleOrden detalleOrden) {
        try{
            detalleOrdenRepository.save(detalleOrden);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public boolean deleteDetalleOrden(String id) {
        try{
            detalleOrdenRepository.deleteById(id);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}