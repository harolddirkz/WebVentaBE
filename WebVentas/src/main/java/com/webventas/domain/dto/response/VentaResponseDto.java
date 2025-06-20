package com.webventas.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class VentaResponseDto {

    private BigDecimal sumaTotalPrecioUnitario;
    private BigDecimal sumaTotalPrecioVenta;
    private BigDecimal sumaTotalUtilidad;
    private BigDecimal sumaTotalDeTotales;
    private List<VentaResponseBdDto> ventas;

}
