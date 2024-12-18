package com.example.grupo2backend.Services;

import com.example.grupo2backend.Entities.Cliente;
import com.example.grupo2backend.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public void saveCliente(Cliente cliente) {
        if (cliente.getNombre() == null || cliente.getEmail() == null || cliente.getTelefono() == null || cliente.getPassword() == null || cliente.getDireccion() == null || cliente.getRole() == null) {
            throw new IllegalArgumentException("Todos los campos del cliente son obligatorios");
        }
        clienteRepository.save(cliente);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findClienteById(String id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Boolean updateCliente(Cliente cliente){
        if (cliente.getNombre() == null || cliente.getEmail() == null || cliente.getTelefono() == null || cliente.getPassword() == null || cliente.getDireccion() == null || cliente.getRole() == null) {
            throw new IllegalArgumentException("Todos los campos del cliente son obligatorios");
        }
        try {
            clienteRepository.save(cliente);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean deleteClienteById(String id) {
        try {
            clienteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
