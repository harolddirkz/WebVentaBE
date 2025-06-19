package com.webventas.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarStockPrecio {

    private Integer idProducto;
    private int cantidadStock;
    private BigDecimal ultimoPrecioUnitario;
    private BigDecimal ultimoPrecioVenta;
}
