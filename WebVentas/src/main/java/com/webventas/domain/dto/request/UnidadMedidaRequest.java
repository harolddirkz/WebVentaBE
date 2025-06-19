package com.webventas.domain.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;

@Data
public class UnidadMedidaRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "El nombre no puede ser vació")
    private String nombreUnidadMedida;

    @NotBlank(message = "La abreviatura puede ser vacío")
    private String abreviatura;
}
