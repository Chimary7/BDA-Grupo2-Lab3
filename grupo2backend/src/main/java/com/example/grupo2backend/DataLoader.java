package com.example.grupo2backend;

import com.example.grupo2backend.Entities.*;
import com.example.grupo2backend.Repositories.*;
import com.example.grupo2backend.utils.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private ValoracionRepository valoracionRepository;

    @Override
    public void run(String... args) throws Exception {
        // Poblar datos de prueba para Cliente
        Cliente cliente1 = new Cliente("1", "Carlos Ruiz", "carlos.ruiz@example.com", "123123123", "clave789", "Calle Luna 789",  Roles.ADMIN);
        clienteRepository.save(cliente1);

        Cliente cliente2 = new Cliente("2", "Ana Torres", "ana.torres@example.com", "321321321", "clave101", "Avenida Sol 101", Roles.CLIENT);
        clienteRepository.save(cliente2);

        // Poblar datos de prueba para Categoria
        Categoria categoria1 = new Categoria("1", "Electrónica");
        categoriaRepository.save(categoria1);

        Categoria categoria2 = new Categoria("2", "Ropa");
        categoriaRepository.save(categoria2);

        Categoria categoria3 = new Categoria("3", "Hogar");
        categoriaRepository.save(categoria3);

        Categoria categoria4 = new Categoria("4", "Deportes");
        categoriaRepository.save(categoria4);

        Categoria categoria5 = new Categoria("5", "Ocio");
        categoriaRepository.save(categoria5);

        // Poblar datos de prueba para Producto
        Producto producto1 = new Producto("1", "1", "Laptop", "Laptop de alta gama", 1200.00, 10, "disponible");
        productoRepository.save(producto1);

        Producto producto2 = new Producto("2", "2", "Camiseta", "Camiseta de algodón", 20.00, 50, "disponible");
        productoRepository.save(producto2);

        Producto producto3 = new Producto("3", "3", "Sofá", "Sofá de 3 plazas", 300.00, 5, "disponible");
        productoRepository.save(producto3);

        Producto producto4 = new Producto("4", "4", "Libro de Java", "Guía completa de Java", 45.00, 20, "disponible");
        productoRepository.save(producto4);

        Producto producto5 = new Producto("5", "5", "Pelota de fútbol", "Pelota de fútbol profesional", 25.00, 30, "disponible");
        productoRepository.save(producto5);

        Producto producto6 = new Producto("5", "5", "Muñeca", "Muñeca de colección", 15.00, 40, "disponible");
        productoRepository.save(producto6);

        // Poblar datos de prueba para Orden
        Orden orden1 = new Orden();
        orden1.setIdOrden("1");
        orden1.setFechaOrden(new Date());
        orden1.setEstado("en_proceso");
        orden1.setId_cliente("2");
        orden1.setId_entrega("1");
        orden1.setTotal(new BigDecimal("1220.00"));
        ordenRepository.save(orden1);

        Orden orden2 = new Orden();
        orden2.setIdOrden("2");
        orden2.setFechaOrden(new Date());
        orden2.setEstado("enviado");
        orden2.setId_cliente("1");
        orden2.setId_entrega("2");
        orden2.setTotal(new BigDecimal("320.00"));
        ordenRepository.save(orden2);

        // Poblar datos de prueba para DetalleOrden
        DetalleOrden detalleOrden1 = new DetalleOrden("1", "1", "1", 1, new BigDecimal("1200.00"));
        detalleOrdenRepository.save(detalleOrden1);

        DetalleOrden detalleOrden2 = new DetalleOrden("2", "2", "2", 2, new BigDecimal("20.00"));
        detalleOrdenRepository.save(detalleOrden2);

        DetalleOrden detalleOrden3 = new DetalleOrden("3", "2", "3", 2, new BigDecimal("300.00"));
        detalleOrdenRepository.save(detalleOrden3);

        // Poblar datos de prueba para Entrega
        Entrega entrega = new Entrega();
        entrega.setId("1");
        entrega.setIdCliente("1");
        entrega.setIdZona("1");
        entrega.setDireccion("Calle Luna 789");
        entrega.setEstado("Pendiente");
        entregaRepository.save(entrega);

        // Poblar datos de prueba para Valoracion
        Valoracion valoracion = new Valoracion("1", "1", "1", 5.0, "Excellent product!");
        valoracionRepository.save(valoracion);
    }
}