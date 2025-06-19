package com.webventas.domain.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class ActualizarProductoRequest {
    private Long idProducto;
    private String nombreProducto;
    private String marca;
    private String descripcion;
    private String estado;
    private Date fechaActivo;
    private Date fechaInactivo;
    private String presentacion;
    private String imagenUrl;
    private Long idUnidadMedida;
    private Long idCategoria;
}
