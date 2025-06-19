package com.webventas.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoriaRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "El nombre de categoria no puede ser vació")
    private String nombre;

    public String descripcion;

}
