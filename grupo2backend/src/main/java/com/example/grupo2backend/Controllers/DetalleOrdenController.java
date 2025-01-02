package com.example.grupo2backend.Controllers;

import com.example.grupo2backend.Entities.DetalleOrden;
import com.example.grupo2backend.Services.DetalleOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalleOrden")
public class DetalleOrdenController {
    @Autowired
    private DetalleOrdenService detalleOrdenService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public void saveDetalleOrden(@RequestBody DetalleOrden detalleOrden) {
        detalleOrdenService.saveDetalleOrden(detalleOrden);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public List<DetalleOrden> getAllDetalleOrden(){
        return detalleOrdenService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENT')")
    public DetalleOrden getDetalleOrdenById(@PathVariable String id) {
        return detalleOrdenService.findDetalleOrdenById(id);
    }

    @GetMapping("/byIdOrden/{idOrden}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENT')")
    public List<DetalleOrden> getDetalleOrdenByIdOrden(@PathVariable String idOrden) {
        return detalleOrdenService.findDetalleOrdenByIdOrden(idOrden);
    }

    @GetMapping("/byIdProducto/{idProducto}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENT')")
    public List<DetalleOrden> getDetalleOrdenByIdProducto(@PathVariable String idProducto) {
        return detalleOrdenService.findDetalleOrdenByIdProducto(idProducto);
    }

    @PutMapping("/")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENT')")
    public void updateDetalleOrden(@RequestBody DetalleOrden detalleOrden) {
        detalleOrdenService.updateDetalleOrden(detalleOrden);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENT')")
    public void deleteDetalleOrden(@PathVariable String id) {
        detalleOrdenService.deleteDetalleOrden(id);
    }
}
