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
        if(valoracion.getValoracion() < 1.0 || valoracion.getValoracion() > 5.0){
            throw new RuntimeException("Error, la valoración debe estar entre 1.0 y 5.0");
        }

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

    public List<Valoracion> findValoracionByIdCliente(String idCliente) {
        return valoracionRepository.findByIdCliente(idCliente);
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

    public Double valoracionPromedioProducto(String idProducto) {
        List<Valoracion> valoraciones = findValoracionByIdProducto(idProducto);

        if (valoraciones.isEmpty()) {
            return 0.0;
        }


        Double promedio = valoraciones.stream()
                .mapToDouble(Valoracion::getValoracion)
                .average()
                .orElse(0.0);

        return Math.round(promedio * 10) / 10.0;
    }

}
