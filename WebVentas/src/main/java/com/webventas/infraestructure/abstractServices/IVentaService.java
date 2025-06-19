package com.webventas.infraestructure.abstractServices;

import com.webventas.domain.dto.request.RegistrarVentaRequest;
import com.webventas.domain.dto.response.VentaResponseDto;

import java.util.List;

public interface IVentaService {
    void registrarVenta(RegistrarVentaRequest request);
    VentaResponseDto ventaPorFecha(String fecha);
    VentaResponseDto ventaEntreFechas(String fechaInicio, String fechaFin);
}
