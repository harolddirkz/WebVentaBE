package com.webventas.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class VentaResponseDto {

    private double sumaTotalPrecioUnitario;
    private double sumaTotalPrecioVenta;
    private double sumaTotalUtilidad;
    private double sumaTotalDeTotales;
    private List<VentaResponseBdDto> ventas;

}
