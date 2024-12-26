package com.example.grupo2backend.Services;

import com.example.grupo2backend.Entities.Orden;
import com.example.grupo2backend.Repositories.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenService {
    @Autowired
    private OrdenRepository ordenRepository;

    public Boolean saveOrden(Orden orden) {
        try{
            ordenRepository.save(orden);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public List<Orden> findAll() {
        return ordenRepository.findAll();
    }

    public Orden findOrdenById(String id) {
        return ordenRepository.findById(id).orElse(null);
    }

    public List<Orden> findById_Cliente(String idCliente) {
        List<Orden> ordenes = ordenRepository.findByIdCliente(idCliente);
        return ordenes;
    }

    public Boolean updateOrden(Orden orden) {
        try{
            ordenRepository.save(orden);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public Boolean deleteOrden(String id) {
        try{
            ordenRepository.deleteById(id);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
