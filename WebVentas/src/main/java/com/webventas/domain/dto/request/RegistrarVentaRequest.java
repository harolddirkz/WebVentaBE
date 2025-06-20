package com.webventas.domain.dto.request;

import com.webventas.utils.TipoComprobante;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class RegistrarVentaRequest {

    private Long idCliente;
    private Long idUsuario;
    private TipoComprobante tipoComprobante;
    private String serieComprobante;
    private Integer numeroComprobante;
    private String metodoPago;
    private Date fechaVenta;
    private BigDecimal total;
    private List<VentaTransaccionRequest> detalles;
}
