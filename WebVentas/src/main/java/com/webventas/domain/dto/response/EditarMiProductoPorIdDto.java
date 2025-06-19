package com.webventas.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditarMiProductoPorIdDto extends ProductosEstadoStockDto{

    private Long idProducto;
    private String nombreProducto;
    private String marca;
    private String descripcion;
    private String presentacion;
    private String imagenUrl;
    private int cantidadStock;
    private Double ultimoPrecioVenta;
    private Double ultimoPrecioUnitario;
    private Long idUnidadMedida;
    private Long idCategoria;

}
