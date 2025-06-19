package com.webventas.domain.dto.request;

import lombok.Data;

@Data
public class VentaTransaccionRequest {

    private Long idProducto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double precioVenta;
    private Double importe;
    private Double utilidad;
}
