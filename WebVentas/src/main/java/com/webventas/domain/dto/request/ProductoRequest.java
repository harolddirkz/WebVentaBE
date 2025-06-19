package com.webventas.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class ProductoRequest {

    @NotBlank(message = "El nombre del proveedor no puede ser vacío")
    private String nombreProducto;
    private String marca;
    private String descripcion;
    @NotBlank(message = "El idUnidadMedidad no puede ser vacío")
    private Long idUnidadMedida;
    @NotBlank(message = "El idCategoria no puede ser vacío")
    private Long idCategoria;
    private Date fechaActivo;
    private String estado;
    private String presentacion;
    private String imagenUrl;
}
