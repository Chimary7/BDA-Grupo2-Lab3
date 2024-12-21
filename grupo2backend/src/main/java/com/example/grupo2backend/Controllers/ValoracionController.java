package com.example.grupo2backend.Controllers;

import com.example.grupo2backend.Entities.Valoracion;
import com.example.grupo2backend.Services.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/valoracion")
public class ValoracionController {
    @Autowired
    private ValoracionService valoracionService;
    @PostMapping("/")
    public Boolean saveValoracion(@RequestBody Valoracion valoracion) {
        return valoracionService.saveValoracion(valoracion);
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
    @PutMapping("/")
    public Boolean updateValoracion(@RequestBody Valoracion valoracion) {
        return valoracionService.updateValoracion(valoracion);
    }
    @DeleteMapping("/{id}")
    public Boolean deleteValoracionById(@PathVariable String id) {
        return valoracionService.deleteValoracionById(id);
    }
}
