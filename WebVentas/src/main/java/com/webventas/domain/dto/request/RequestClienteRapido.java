package com.webventas.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestClienteRapido {

    @NotBlank(message ="El tipo de documento no puede estar vacío")
    private String tipoDocumento;

    @NotBlank(message ="Es obligatorio ingresar el numero de documento")
    private String numeroDocumento;

    private String contacto;
}
