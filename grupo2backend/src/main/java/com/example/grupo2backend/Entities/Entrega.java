package com.example.grupo2backend.Entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "entrega")
public class Entrega {

    @Id
    private String id;
    private String id_zona;
    private String id_cliente;

    private GeoJsonPoint coordenada_direccion;

    private String direccion;
    private String estado;

    // Getters
    public String getId() { return id; }


    public String getId_zona() { return id_zona; }


    public String getId_cliente() { return id_cliente; }

    public GeoJsonPoint getCoordenada_direccion() { return coordenada_direccion; }

    public String getEstado() { return estado; }

    public String getDireccion() { return direccion; }

    // Setters

    public void setId(String id) { this.id = id; }

    public void setId_cliente(String id_cliente) { this.id_cliente = id_cliente; }

    public void setId_zona(String id_zona) { this.id_zona = id_zona; }

    public void setId_entrega(String id_pedido) { this.id = id; }

    public void setCoordenada_direccion(GeoJsonPoint coordenada_direccion) { this.coordenada_direccion = coordenada_direccion; }


    public void setDireccion(String direccion) { this.direccion = direccion; }

    public void setEstado(String estado) { this.estado = estado; }

}
