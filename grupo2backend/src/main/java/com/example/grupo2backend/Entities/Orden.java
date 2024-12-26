package com.example.grupo2backend.Entities;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "orden")
public class Orden {
    private String id;
    private Date fecha_orden;
    private String estado;

    private String idCliente;
    private String id_entrega;
    private BigDecimal total;

    public String getId_orden() {
        return id;
    }

    public void setId_orden(String id) {
        this.id = id;
    }

    public Date getFecha_orden() {
        return fecha_orden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fecha_orden = fechaOrden;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId_cliente() {
        return idCliente;
    }

    public void setId_cliente(String id_cliente) {
        this.idCliente = id_cliente;
    }

    public void setFecha_orden(Date fecha_orden) {
        this.fecha_orden = fecha_orden;
    }

    public String getId_entrega() {
        return id_entrega;
    }

    public void setId_entrega(String id_entrega) {
        this.id_entrega = id_entrega;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
