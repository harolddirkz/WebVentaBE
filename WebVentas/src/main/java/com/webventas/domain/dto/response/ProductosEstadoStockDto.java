package com.webventas.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductosEstadoStockDto {
    private Long idProducto;
    private String nombreProducto;
    private String marca;
    private String descripcion;
    private String presentacion;
    private String imagenUrl;
    private int cantidadStock;
    private Double ultimoPrecioUnitario;
    private Double ultimoPrecioVenta;

}
