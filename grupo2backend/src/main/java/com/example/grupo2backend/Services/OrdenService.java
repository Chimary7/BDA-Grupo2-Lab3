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

    public List<Orden> findById_Cliente(String idCliente) {
        List<Orden> ordenes = ordenRepository.findByIdCliente(idCliente);
        return ordenes;
    }
}
