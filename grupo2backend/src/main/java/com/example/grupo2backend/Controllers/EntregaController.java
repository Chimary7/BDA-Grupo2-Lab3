package com.example.grupo2backend.Controllers;

import com.example.grupo2backend.Services.EntregaService;
import com.example.grupo2backend.Entities.Entrega;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entrega")
public class EntregaController {
    @Autowired
    private EntregaService entregaService;

    @PostMapping("/")
    public void saveEntrega(@RequestBody Entrega entrega) {
        entregaService.guardar(entrega);
    }

    @PutMapping("/")
    public void updateEntrega(@RequestBody Entrega entrega) {
        entregaService.updateEntrega(entrega);
    }

    @GetMapping("/")
    public List<Entrega> getAllEntregas() {
        return entregaService.getAllPedidos();
    }

    @GetMapping("/{id}")
    public Entrega getEntregaById(@PathVariable String id) {
        return entregaService.getPedidoById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEntregaById(@PathVariable String id) {
        entregaService.deletePedido(id);
    }
}
