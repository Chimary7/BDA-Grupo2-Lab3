package com.example.grupo2backend.Entities;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "orden")
public class Orden {
    private String id;
    private Date fechaOrden;
    private String estado;
    private String idCliente;
    private String idEntrega;
    private BigDecimal total;


    public Orden(String id, Date fechaOrden, String estado, String idCliente, String idEntrega, BigDecimal total) {
        this.id = id;
        this.fechaOrden = fechaOrden;
        this.estado = estado;
        this.idCliente = idCliente;
        this.idEntrega = idEntrega;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(String idEntrega) {
        this.idEntrega = idEntrega;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
