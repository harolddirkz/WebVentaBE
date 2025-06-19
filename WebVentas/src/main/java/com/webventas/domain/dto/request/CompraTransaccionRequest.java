package com.webventas.domain.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class CompraTransaccionRequest {
    private Long idProducto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double precioVenta;
    private Double importe;
}
