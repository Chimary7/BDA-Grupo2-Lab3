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

    public String getIdOrden() {
        return id;
    }

    public void setIdOrden(String id) {
        this.id = id;
    }

    public Date getFecha_orden() {
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

    public String getId_cliente() {
        return idCliente;
    }

    public void setId_cliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public void setFecha_orden(Date fecha_orden) {
        this.fechaOrden = fecha_orden;
    }

    public String getId_entrega() {
        return idEntrega;
    }

    public void setId_entrega(String id_entrega) {
        this.idEntrega = id_entrega;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
