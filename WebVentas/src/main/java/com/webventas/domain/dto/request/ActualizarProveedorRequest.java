package com.webventas.domain.dto.request;

import lombok.Data;

@Data
public class ActualizarProveedorRequest {

    private Long idProveedor;

    private String nombreProveedor;

    private String contacto;

    private String direccion;
}
