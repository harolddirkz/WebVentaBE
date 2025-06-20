package com.webventas.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaResponseBdDto {
    private String nombreProducto;
    private Date fechaVenta;
    private Long idVenta;
    private String serieComprobante;
    private int numeroComprobante;
    private BigDecimal precioUnitario;
    private int cantidad;
    private BigDecimal precioVenta;
    private BigDecimal utilidad;
    private BigDecimal total;
}
