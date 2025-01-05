package com.example.grupo2backend.Controllers;

import com.example.grupo2backend.Entities.ArchivosProducto;
import com.example.grupo2backend.Entities.Categoria;
import com.example.grupo2backend.Entities.Producto;
import com.example.grupo2backend.Entities.Valoracion;
import com.example.grupo2backend.Repositories.CategoriaRepository;
import com.example.grupo2backend.Services.ArchivosProductosService;
import com.example.grupo2backend.Services.CategoriaService;
import com.example.grupo2backend.Services.ProductoService;
import com.example.grupo2backend.Services.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired
    private ArchivosProductosService archivosProductosService;
    @Autowired
    private ValoracionService valoracionService;

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

    @GetMapping("/archivos/{idProducto}")
    public List<ArchivosProducto> getArchivosProductoByIdProducto(@PathVariable String idProducto) {
        return archivosProductosService.findArchivoProductoByIdProducto(idProducto);
    }

    @GetMapping("/archivos/{idProducto}/{tipo}")
    public List<ArchivosProducto> getArchivosProductoByIdProductoAndTipo(@PathVariable String idProducto, @PathVariable String tipo) {
        return archivosProductosService.findArchivoProductoByIdProductoAndTipo(idProducto, tipo);
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
    public String createProducto(@RequestBody Producto producto) {
        return productoService.saveProducto(producto);
    }

    @GetMapping("/valoracionProducto/{idProducto}")
    public Double getPromedioValoracionProducto(@PathVariable String idProducto) {
        return valoracionService.valoracionPromedioProducto(idProducto);
    }

    @GetMapping("/producto/{idProducto}")
    public List<Valoracion> getValoracionByIdProducto(@PathVariable String idProducto) {
        return valoracionService.findValoracionByIdProducto(idProducto);
    }
}
