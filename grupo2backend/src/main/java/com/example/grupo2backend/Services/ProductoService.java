package com.example.grupo2backend.Services;

import com.example.grupo2backend.Entities.Producto;
import com.example.grupo2backend.Repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public Boolean saveProducto(Producto producto) {
        try {
            productoRepository.save(producto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Producto> findAll() {return productoRepository.findAll();}

    public Producto findProductoById(String id) {return productoRepository.findById(id).orElse(null);}

    public List<Producto> findProductoByIdCategoria(String idCategoria) {
        return productoRepository.findByIdCategoria(idCategoria);
    }

    public Boolean updateProducto(Producto producto){
        try {
            productoRepository.save(producto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean deleteProductoById(String id) {
        try {
            productoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
