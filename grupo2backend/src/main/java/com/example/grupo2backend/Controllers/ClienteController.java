package com.example.grupo2backend.Controllers;

import com.example.grupo2backend.Entities.Cliente;
import com.example.grupo2backend.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/")
    public void saveCliente(@RequestBody  Cliente cliente) {
        clienteService.saveCliente(cliente);
    }

    @GetMapping("/")
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
