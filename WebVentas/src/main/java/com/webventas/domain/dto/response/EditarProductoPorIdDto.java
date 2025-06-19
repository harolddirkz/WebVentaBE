package com.webventas.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditarProductoPorIdDto {

    private Long idProducto;
    private String nombreProducto;
    private String marca;
    private String descripcion;
    private String estado;
    private String presentacion;
    private String imagenUrl;
    private Long idUnidadMedida;
    private Long idCategoria;
    private Date fechaActivo;
    private Date fechaInactivo;
}
