package com.webventas.domain.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class RegistrarCompraRequest {
    private Long idProveedor;
    private Long idUsuario;
    private String tipoComprobante;
    private String numeroComprobante;
    private Double total;
    List<CompraTransaccionRequest> detalles;

}
