package com.example.grupo2backend.Controllers;

import com.example.grupo2backend.Entities.Orden;
import com.example.grupo2backend.Services.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orden")
public class OrdenController {
    @Autowired
    private OrdenService ordenService;

    @PostMapping("/")
    public void saveOrden(@RequestBody Orden orden){
        ordenService.saveOrden(orden);
    }

    @GetMapping("/")
    public List<Orden> getAllOrdenes(){
        return ordenService.findAll();
    }

    @GetMapping("/{id}")
    public Orden getOrdenById(@PathVariable String id){
        return ordenService.findOrdenById(id);
    }

    @GetMapping("/getByIdUser/{id}")
    public List<Orden> getOrdenesByIdCliente(@PathVariable String id) {
        return ordenService.findById_Cliente(id);
    }

    @PutMapping("/")
    public void updateOrden(@RequestBody Orden orden){
        ordenService.updateOrden(orden);
    }

    @DeleteMapping("/{id}")
    public void deleteOrden(@PathVariable String id){
        ordenService.deleteOrden(id);
    }
}
