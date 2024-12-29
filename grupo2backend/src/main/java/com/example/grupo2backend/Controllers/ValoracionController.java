package com.example.grupo2backend.Controllers;

import com.example.grupo2backend.Entities.Valoracion;
import com.example.grupo2backend.Services.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/valoracion")
public class ValoracionController {
    @Autowired
    private ValoracionService valoracionService;

    @PostMapping("/")
    public ResponseEntity<?> saveValoracion(@RequestBody Valoracion valoracion) {
        try {
            Boolean result = valoracionService.saveValoracion(valoracion);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public List<Valoracion> getAllValoraciones() {
        return valoracionService.findAll();
    }

    @GetMapping("/{id}")
    public Valoracion getValoracionById(@PathVariable String id) {
        return valoracionService.findValoracionById(id);
    }

    @GetMapping("/producto/{idProducto}")
    public List<Valoracion> getValoracionByIdProducto(@PathVariable String idProducto) {
        return valoracionService.findValoracionByIdProducto(idProducto);
    }

    @GetMapping("/cliente/{idCliente}")
    public List<Valoracion> getValoracionByIdCliente(@PathVariable String idCliente) {
        return valoracionService.findValoracionByIdCliente(idCliente);
    }

    @PutMapping("/")
    public Boolean updateValoracion(@RequestBody Valoracion valoracion) {
        return valoracionService.updateValoracion(valoracion);
    }
    @DeleteMapping("/{id}")
    public Boolean deleteValoracionById(@PathVariable String id) {
        return valoracionService.deleteValoracionById(id);
    }

    @GetMapping("/valoracionProducto/{idProducto}")
    public Double getPromedioValoracionProducto(@PathVariable String idProducto) {
        return valoracionService.valoracionPromedioProducto(idProducto);
    }
}
