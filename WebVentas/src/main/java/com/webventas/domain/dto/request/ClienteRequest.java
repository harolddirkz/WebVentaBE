package com.webventas.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class ClienteRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message ="El tipo de documento no puede estar vacío")
    private String tipoDocumento;

    @NotBlank(message ="Es obligatorio ingresar el numero de documento")
    private String numeroDocumento;

    @NotBlank(message ="Es obligatorio ingresar el nombre del cliente")
    private String nombreCliente;
    private String contacto;
    private String direccion;
}
