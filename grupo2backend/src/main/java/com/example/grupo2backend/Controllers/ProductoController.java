package com.example.grupo2backend.Controllers;

import com.example.grupo2backend.Entities.Producto;
import com.example.grupo2backend.Services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    @PostMapping("/")
    public Boolean saveProducto(@RequestBody Producto producto) {
        return productoService.saveProducto(producto);
    }
    @GetMapping("/")
    public List<Producto> getAllProductos() {
        return productoService.findAll();
    }
    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable String id) {
        return productoService.findProductoById(id);
    }
    @GetMapping("/categoria/{idCategoria}")
    public List<Producto> getProductoByIdCategoria(@PathVariable String idCategoria) {
        return productoService.findProductoByIdCategoria(idCategoria);
    }
    @PutMapping("/")
    public Boolean updateProducto(@RequestBody Producto producto) {
        return productoService.updateProducto(producto);
    }
    @DeleteMapping("/{id}")
    public Boolean deleteProductoById(@PathVariable String id) {
        return productoService.deleteProductoById(id);
    }

    @GetMapping("/byDetalleOrden/{id}")
    public List<Producto> getProductoByDetalleOrden(@PathVariable String id) {
        return productoService.findProductoByDetalleOrden(id);
    }
}
