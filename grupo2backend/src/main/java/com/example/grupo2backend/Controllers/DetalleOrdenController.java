package com.example.grupo2backend.Controllers;

import com.example.grupo2backend.Entities.DetalleOrden;
import com.example.grupo2backend.Services.DetalleOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalleOrden")
public class DetalleOrdenController {
    @Autowired
    private DetalleOrdenService detalleOrdenService;

    @PostMapping("/")
    public void saveDetalleOrden(@RequestBody DetalleOrden detalleOrden) {
        detalleOrdenService.saveDetalleOrden(detalleOrden);
    }

    @GetMapping("/")
    public List<DetalleOrden> getAllDetalleOrden(){
        return detalleOrdenService.findAll();
    }

    @GetMapping("/{id}")
    public DetalleOrden getDetalleOrdenById(@PathVariable String id) {
        return detalleOrdenService.findDetalleOrdenById(id);
    }

    @GetMapping("/byIdOrden/{idOrden}")
    public List<DetalleOrden> getDetalleOrdenByIdOrden(@PathVariable String idOrden) {
        return detalleOrdenService.findDetalleOrdenByIdOrden(idOrden);
    }

    @GetMapping("/byIdProducto/{idProducto}")
    public List<DetalleOrden> getDetalleOrdenByIdProducto(@PathVariable String idProducto) {
        return detalleOrdenService.findDetalleOrdenByIdProducto(idProducto);
    }

    @PutMapping("/")
    public void updateDetalleOrden(@RequestBody DetalleOrden detalleOrden) {
        detalleOrdenService.updateDetalleOrden(detalleOrden);
    }

    @DeleteMapping("/{id}")
    public void deleteDetalleOrden(@PathVariable String id) {
        detalleOrdenService.deleteDetalleOrden(id);
    }
}
