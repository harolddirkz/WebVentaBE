package com.webventas.domain.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class RegistrarVentaRequest {

    private Long idCliente;
    private Long idUsuario;
    private String tipoComprobante;
    private String serieComprobante;
    private String numeroComprobante;
    private String metodoPago;
    private LocalDate fechaVenta;
    private Double total;
    private List<VentaTransaccionRequest> detalles;
}
