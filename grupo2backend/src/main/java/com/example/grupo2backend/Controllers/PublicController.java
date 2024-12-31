package com.example.grupo2backend.Controllers;

import com.example.grupo2backend.Entities.Categoria;
import com.example.grupo2backend.Entities.Producto;
import com.example.grupo2backend.Repositories.CategoriaRepository;
import com.example.grupo2backend.Services.CategoriaService;
import com.example.grupo2backend.Services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/categorias")
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    @GetMapping("/productos")
    public List<Producto> getAllProductos() {
        return productoService.findAll();
    }

    @GetMapping("/productos/categoria/{idCategoria}")
    public List<Producto> getProductoByIdCategoria(@PathVariable String idCategoria) {
        return productoService.findProductoByIdCategoria(idCategoria);
    }

    @GetMapping("/productos/{id}")
    public Producto getProductoById(@PathVariable String id) {
        return productoService.findProductoById(id);
    }

    @GetMapping("/categorias/{id}")
    public Categoria getCategoriaById(@PathVariable String id) {
        return categoriaService.findCategoriaById(id);
    }

    @PutMapping("/productos")
    public Boolean updateProducto(@RequestBody Producto producto) {
        return productoService.updateProducto(producto);
    }

    @DeleteMapping("/productos/{id}")
    public Boolean deleteProductoById(@PathVariable String id) {
        return productoService.deleteProductoById(id);
    }

    @PostMapping("/productos")
    public Boolean createProducto(@RequestBody Producto producto) {
        return productoService.saveProducto(producto);
    }
}
