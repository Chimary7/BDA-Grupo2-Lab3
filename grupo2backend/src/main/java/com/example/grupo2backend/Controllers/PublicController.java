package com.example.grupo2backend.Controllers;

import com.example.grupo2backend.Entities.Categoria;
import com.example.grupo2backend.Entities.Producto;
import com.example.grupo2backend.Repositories.CategoriaRepository;
import com.example.grupo2backend.Services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoService productoService;

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

}
