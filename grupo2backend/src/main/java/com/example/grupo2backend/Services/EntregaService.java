package com.example.grupo2backend.Services;

import com.example.grupo2backend.Entities.Entrega;
import com.example.grupo2backend.Repositories.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregaService {
    @Autowired
    private EntregaRepository entregaRepository;

    public ResponseEntity<Object> guardar(Entrega entrega) {
        try {
            entregaRepository.save(entrega);
            return new ResponseEntity<>("Se ingresó correctamente la entrega", HttpStatus.CREATED);
        } catch (Exception e) {
            // Manejo del error
            return new ResponseEntity<>("Error al ingresar la entrega: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Entrega> getAllPedidos() {
        return entregaRepository.findAll();
    }

    public Entrega getPedidoById(String id) {
        return entregaRepository.findById(id).orElse(null);
    }

    public ResponseEntity<Object> deletePedido(String id) {
        try {
            entregaRepository.deleteById(id);
            return new ResponseEntity<>("Se eliminó correctamente la entrega", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la entrega: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void updateEntrega(Entrega entrega) {
        try {
            entregaRepository.save(entrega);
        } catch (Exception e) {
            throw new RuntimeException("Error updating Entrega: " + e.getMessage());
        }
    }


}
