package com.webventas.infraestructure.abstractServices;

import com.webventas.domain.dto.response.ComprobanteResponseDto;
import com.webventas.utils.TipoComprobante;

public interface IComprobanteService {
    ComprobanteResponseDto obtenerNumeracion(String tipo);
}
