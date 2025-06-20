package com.webventas.domain.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VentaTransaccionRequest {

    private Long idProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal precioVenta;
    private BigDecimal importe;
    private BigDecimal utilidad;
}
