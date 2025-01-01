package com.example.grupo2backend.Controllers;

import com.example.grupo2backend.Entities.ArchivosProducto;
import com.example.grupo2backend.Services.ArchivosProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/archivosproducto")
public class ArchivosProductoController {
    @Autowired
    private ArchivosProductosService archivosProductoService;

    @PostMapping("/")
    public Boolean saveArchivosProducto(@RequestBody ArchivosProducto archivosProducto) {
        return archivosProductoService.saveArchivoProducto(archivosProducto);
    }

    @GetMapping("/")
    public List<ArchivosProducto> getAllArchivosProductos() {
        return archivosProductoService.findAll();
    }

    @GetMapping("/{id}")
    public ArchivosProducto getArchivosProductoById(@PathVariable String id) {
        return archivosProductoService.findArchivoProductoById(id);
    }

    @GetMapping("/archivos/{idProducto}")
    public List<ArchivosProducto> getArchivosProductoByIdProducto(@PathVariable String idProducto) {
        return archivosProductoService.findArchivoProductoByIdProducto(idProducto);
    }

    @PutMapping("/")
    public Boolean updateArchivosProducto(@RequestBody ArchivosProducto archivosProducto) {
        return archivosProductoService.updateArchivoProducto(archivosProducto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteArchivosProductoById(@PathVariable String id) {
        return archivosProductoService.deleteArchivoProductoById(id);
    }

}
