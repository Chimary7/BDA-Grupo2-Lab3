package com.example.grupo2backend.Controllers;

import com.example.grupo2backend.Entities.Orden;
import com.example.grupo2backend.Services.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orden")
public class OrdenController {
    @Autowired
    private OrdenService ordenService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENT')")
    public void saveOrden(@RequestBody Orden orden){
        ordenService.saveOrden(orden);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Orden> getAllOrdenes(){
        return ordenService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENT')")
    public Orden getOrdenById(@PathVariable String id){
        return ordenService.findOrdenById(id);
    }

    @GetMapping("/getByIdUser/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENT')")
    public List<Orden> getOrdenesByIdCliente(@PathVariable String id) {
        return ordenService.findById_Cliente(id);
    }

    @PutMapping("/")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENT')")
    public void updateOrden(@RequestBody Orden orden){
        ordenService.updateOrden(orden);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteOrden(@PathVariable String id){
        ordenService.deleteOrden(id);
    }
}
