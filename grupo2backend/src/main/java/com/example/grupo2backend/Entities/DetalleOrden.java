package com.example.grupo2backend.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "detalleOrden")
public class DetalleOrden {

    @Id
    private String id;
    private String idOrden;
    private String idProducto;
    private Integer cantidad;
    private BigDecimal precio_unitario;

    public DetalleOrden(String id, String idOrden, String idProducto, Integer cantidad,
                        BigDecimal precio_unitario) {
        this.id = id;
        this.idOrden = idOrden;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }

    public DetalleOrden() {
    }

    public String getId() {
        return id;
    }

    public void setId_detalle(String id) {
        this.id = id;
    }

    public String getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(BigDecimal precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
}
