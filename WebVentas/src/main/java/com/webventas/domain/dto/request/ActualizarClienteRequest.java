package com.webventas.domain.dto.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ActualizarClienteRequest {

    private Long idCliente;

    private String tipoDocumento;

    private String numeroDocumento;

    private String nombreCliente;

    private String contacto;

    private String direccion;
}
