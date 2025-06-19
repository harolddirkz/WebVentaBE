package com.webventas.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaResponseBdDto {
    private String nombreProducto;
    private Date fechaVenta;
    private Long idVenta;
    private String numeroComprobante;
    private Double precioUnitario;
    private int cantidad;
    private Double precioVenta;
    private Double utilidad;
    private Double total;
}
