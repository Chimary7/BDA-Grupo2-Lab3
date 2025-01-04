package com.example.grupo2backend.Entities;

import com.example.grupo2backend.utils.TipoArchivosProducto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "archivosproducto")
public class ArchivosProducto {
    @Id
    private String id;

    private String idProducto;
    private String nombre;

    private TipoArchivosProducto tipo;

    private String url;

    public ArchivosProducto(String id, String idProducto, String nombre,TipoArchivosProducto tipo, String url) {
        this.id = id;
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.tipo = tipo;
        this.url = url;
    }


    public TipoArchivosProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoArchivosProducto tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
