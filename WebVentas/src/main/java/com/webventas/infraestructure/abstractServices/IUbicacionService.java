package com.webventas.infraestructure.abstractServices;

import com.webventas.domain.dto.request.UbicacionRequest;
import com.webventas.domain.entities.Ubicacion;

import java.util.List;

public interface IUbicacionService {
    Ubicacion create(UbicacionRequest ubicacionRequest);
    void deleteUbicacion(Long id);
    List<Ubicacion> findAllUbicaciones();
}
