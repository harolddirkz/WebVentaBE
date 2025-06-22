package com.webventas.domain.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CompraTransaccionRequest {
    private Long idProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal precioVenta;
    private BigDecimal importe;
}
