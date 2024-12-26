package com.example.grupo2backend.Entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "entrega")
public class Entrega {

    @Id
    private String id;
    private String idZona;
    private String idCliente;

    private GeoJsonPoint coordenadaDireccion;

    private String direccion;
    private String estado;

    // Getters
    public String getId() { return id; }


    public String getIdZona() { return idZona; }


    public String getIdCliente() { return idCliente; }

    public GeoJsonPoint getCoordenadaDireccion() { return coordenadaDireccion; }

    public String getEstado() { return estado; }

    public String getDireccion() { return direccion; }

    // Setters

    public void setId(String id) { this.id = id; }

    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }

    public void setIdZona(String idZona) { this.idZona = idZona; }

    public void setIdEntrega(String id) { this.id = id; }

    public void setCoordenadaDireccion(GeoJsonPoint coordenadaDireccion) { this.coordenadaDireccion = coordenadaDireccion; }


    public void setDireccion(String direccion) { this.direccion = direccion; }

    public void setEstado(String estado) { this.estado = estado; }

}
