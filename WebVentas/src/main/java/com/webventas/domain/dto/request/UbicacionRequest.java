package com.webventas.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class UbicacionRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "El codigo de ubicacion no puede ser vació")
    private String codigoUbicacion;

    private String descripcionUbicacion;

}
