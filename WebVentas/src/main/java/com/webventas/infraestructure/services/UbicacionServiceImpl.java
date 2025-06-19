package com.webventas.infraestructure.services;

import com.webventas.domain.dto.request.UbicacionRequest;
import com.webventas.domain.entities.Ubicacion;
import com.webventas.domain.repositories.UbicacionRepository;
import com.webventas.infraestructure.abstractServices.IUbicacionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbicacionServiceImpl implements IUbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    @Override
    public Ubicacion create(UbicacionRequest ubicacionRequest) {
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setCodigoUbicacion(ubicacionRequest.getCodigoUbicacion());
        ubicacion.setDescripcionUbicacion(ubicacionRequest.getDescripcionUbicacion());
        return ubicacionRepository.saveAndFlush(ubicacion);
    }

    @Transactional
    @Override
    public void deleteUbicacion(Long id) {
        if (!ubicacionRepository.existsById(id)) {
            throw new RuntimeException("Ubicacion no encontrada con id: " + id);
        }
        ubicacionRepository.deleteById(id);
    }

    @Override
    public List<Ubicacion> findAllUbicaciones() {
        return ubicacionRepository.findAll();
    }
}
