package com.webventas.domain.repositories;

import com.webventas.domain.dto.response.VentaResponseBdDto;
import com.webventas.domain.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Long> {

    @Query(value = """

        SELECT p.nombreProducto, v.fechaVenta, v.idVenta, c.serieComprobante, c.numeroComprobante, dt.precioUnitario, dt.cantidad, dt.precioVenta, dt.utilidad,v.total
        FROM ventas v
        INNER JOIN detalleTransacciones dt ON v.idVenta = dt.idreferencia
        INNER JOIN productos p ON dt.idproducto = p.idProducto
    	INNER JOIN comprobantes c ON v.idComprobante = c.idComprobante
        WHERE dt.tipoTransaccion = 'VENTA' AND DATE(v.fechaVenta) = DATE(:fechaVenta)
    """, nativeQuery = true)
    List<VentaResponseBdDto> obtenerVentasPorFecha(@Param("fechaVenta") String fecha);

    @Query(value = """

        SELECT p.nombreProducto, v.fechaVenta, v.idVenta, c.serieComprobante, c.numeroComprobante, dt.precioUnitario, dt.cantidad, dt.precioVenta, dt.utilidad,v.total
        FROM ventas v
        INNER JOIN detalleTransacciones dt ON v.idVenta = dt.idreferencia
        INNER JOIN productos p ON dt.idproducto = p.idProducto
    	INNER JOIN comprobantes c ON v.idComprobante = c.idComprobante
        WHERE dt.tipoTransaccion = 'VENTA'
      AND DATE(v.fechaVenta) BETWEEN DATE(:fechaInicio) AND DATE(:fechaFin)
    """, nativeQuery = true)
    List<VentaResponseBdDto> obtenerVentasEntreFechas(
            @Param("fechaInicio") String fechaInicio,
            @Param("fechaFin") String fechaFin
    );


}
