package com.webventas.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProveedorRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "El nombre del proveedor no puede ser vació")
    private String nombreProveedor;

    private String contacto;

    private String direccion;


}
