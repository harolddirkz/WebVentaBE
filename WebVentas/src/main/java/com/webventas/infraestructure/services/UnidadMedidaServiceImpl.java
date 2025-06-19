package com.webventas.infraestructure.services;

import com.webventas.domain.dto.request.UnidadMedidaRequest;
import com.webventas.domain.dto.request.UnidadMedidaUpdate;
import com.webventas.domain.entities.Categoria;
import com.webventas.domain.entities.UnidadMedida;
import com.webventas.domain.repositories.UnidadMedidaRepository;
import com.webventas.infraestructure.abstractServices.IUnidadMedidaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadMedidaServiceImpl implements IUnidadMedidaService {

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

    @Override
    public UnidadMedida create(UnidadMedidaRequest unidadMedida) {
        UnidadMedida unidadEntity = new UnidadMedida();
        unidadEntity.setNombreUnidadMedida(unidadMedida.getNombreUnidadMedida());
        unidadEntity.setAbreviatura(unidadMedida.getAbreviatura());
        return unidadMedidaRepository.saveAndFlush(unidadEntity);
    }

    @Transactional
    @Override
    public void deleteUnidadMedidad(Long id) {
        if (!unidadMedidaRepository.existsById(id)) {
            throw new RuntimeException("Unidad de medida no encontrada con id: " + id);
        }
        unidadMedidaRepository.deleteById(id);
    }

    @Override
    public List<UnidadMedida> findAllUnidadMedidas() {
        return unidadMedidaRepository.findAll();
    }

    @Override
    public void editUnidadMedida(UnidadMedidaUpdate unidadMedida) {
        UnidadMedida unidad = unidadMedidaRepository.findById(Long.valueOf(unidadMedida.getIdUnidadMedida()))
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + unidadMedida.getIdUnidadMedida()));

        unidad.setNombreUnidadMedida(unidadMedida.getNombreUnidadMedida());
        unidad.setAbreviatura(unidadMedida.getAbreviatura());

        unidadMedidaRepository.saveAndFlush(unidad);
    }
}
