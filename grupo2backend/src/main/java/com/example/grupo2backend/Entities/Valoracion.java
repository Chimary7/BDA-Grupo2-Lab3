package com.example.grupo2backend.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "valoracion")
public class Valoracion {
    @Id
    private String id;

    private String idCliente;
    private String idProducto;
    private Double valoracion;
    private String comentario;


    public Valoracion(String id, String idCliente, String idProducto, Double valoracion, String comentario) {
        this.id = id;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.valoracion = valoracion;
        this.comentario = comentario;
    }

    public Valoracion() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public Double getValoracion() {
        return valoracion;
    }

    public void setValoracion(Double valoracion) {
        this.valoracion = valoracion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
