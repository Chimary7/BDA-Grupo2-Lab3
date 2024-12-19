package com.example.grupo2backend.Services;

import com.example.grupo2backend.Entities.Valoracion;
import com.example.grupo2backend.Repositories.ValoracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValoracionService {
    @Autowired
    private ValoracionRepository valoracionRepository;

    public Boolean saveValoracion(Valoracion valoracion) {
        System.out.println(valoracion);
        try {
            valoracionRepository.save(valoracion);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Valoracion> findAll() {
        return valoracionRepository.findAll();
    }

    public Valoracion findValoracionById(String id) {
        return valoracionRepository.findById(id).orElse(null);
    }

    public List<Valoracion> findValoracionByIdProducto(String idProducto) {
        return valoracionRepository.findByIdProducto(idProducto);
    }

    public Boolean updateValoracion(Valoracion valoracion){
        try {
            valoracionRepository.save(valoracion);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean deleteValoracionById(String id) {
        try {
            valoracionRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
