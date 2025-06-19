package com.webventas.infraestructure.abstractServices;

import com.webventas.domain.dto.request.UnidadMedidaRequest;
import com.webventas.domain.dto.request.UnidadMedidaUpdate;
import com.webventas.domain.entities.UnidadMedida;

import java.util.List;

public interface IUnidadMedidaService {
    UnidadMedida create (UnidadMedidaRequest unidadMedida);
    void deleteUnidadMedidad(Long id);
    List<UnidadMedida> findAllUnidadMedidas();
    void editUnidadMedida(UnidadMedidaUpdate unidadMedida);
}
