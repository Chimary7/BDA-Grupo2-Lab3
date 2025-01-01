package com.example.grupo2backend.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "archivosproducto")
public class ArchivosProducto {
    @Id
    private String id;

    private String idProducto;
    private String nombre;

    private String url;

}
