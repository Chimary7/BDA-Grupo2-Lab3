package com.example.grupo2backend.Controllers;

import com.example.grupo2backend.Entities.Cliente;
import com.example.grupo2backend.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@PreAuthorize("hasRole('ADMIN') or hasRole('CLIENT')")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public void saveCliente(@RequestBody Cliente cliente) {
        clienteService.saveCliente(cliente);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Cliente> getAllClientes() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable String id) {
        return clienteService.findClienteById(id);
    }

    @PutMapping("/")
    public Boolean updateCliente(@RequestBody Cliente cliente) {
        return clienteService.updateCliente(cliente);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteClienteById(@PathVariable String id) {
        return clienteService.deleteClienteById(id);
    }
}
