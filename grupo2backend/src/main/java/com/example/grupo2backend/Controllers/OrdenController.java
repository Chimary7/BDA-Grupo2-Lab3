package com.example.grupo2backend.Controllers;

import com.example.grupo2backend.Entities.Orden;
import com.example.grupo2backend.Services.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orden")
public class OrdenController {
    @Autowired
    private OrdenService ordenService;

    @GetMapping("/getByIdUser/{id}")
    public List<Orden> getOrdenesByIdCliente(@PathVariable String id) {
        return ordenService.findById_Cliente(id);
    }
}
