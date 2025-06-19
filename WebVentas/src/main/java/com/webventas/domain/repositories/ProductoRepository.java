package com.webventas.domain.repositories;

import com.webventas.domain.dto.response.EditarMiProductoPorIdDto;
import com.webventas.domain.dto.response.EditarProductoPorIdDto;
import com.webventas.domain.dto.response.ProductosEstadoStockDto;
import com.webventas.domain.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {

    @Query(value = """
    SELECT p.* FROM Productos p
    WHERE p.Estado = 'Inactivo'
    """, nativeQuery = true)
    List<Producto> listarProductosInactivos();

    @Query(value = """
    SELECT p.* FROM Productos p
    WHERE p.Estado = 'ACTIVO'
    """, nativeQuery = true)
    List<Producto> listarProductosActivos();

    @Query(value = """
    SELECT DISTINCT ON (p.idProducto) p.idProducto, p.nombreProducto, p.marca, p.descripcion, p.presentacion, p.imagenUrl,
           s.cantidadStock, p.ultimoPrecioUnitario, p.ultimoPrecioVenta FROM Productos p
    INNER JOIN Stock s on p.idProducto = s.idProducto
    WHERE p.Estado = 'ACTIVO' AND s.CantidadStock >= 1
    """, nativeQuery = true)
    List<ProductosEstadoStockDto> listarProductosConStock();

    @Query(value = """
    SELECT DISTINCT ON (p.idProducto) p.idProducto, p.nombreProducto, p.marca, p.descripcion, p.presentacion, p.imagenUrl,
           s.cantidadStock, p.ultimoPrecioUnitario, p.ultimoPrecioVenta, p.idUnidadMedida, p.idCategoria FROM Productos p
    INNER JOIN Stock s on p.idProducto = s.idProducto
    WHERE p.Estado = 'ACTIVO' AND s.CantidadStock >= 1 AND p.idProducto = :idProducto
    """, nativeQuery = true)
    EditarMiProductoPorIdDto editarMiProductoPorId(@Param("idProducto") Long idProducto);


    @Query(value = """
    SELECT DISTINCT ON (p.idProducto) p.idProducto, p.nombreProducto, p.marca, p.descripcion, p.estado,
      p.presentacion, p.imagenUrl, p.idUnidadMedida, p.idCategoria, p.fechaActivo, p.fechaInactivo FROM Productos p
    WHERE p.Estado = 'ACTIVO'  AND p.idProducto = :idProducto
    """, nativeQuery = true)
    EditarProductoPorIdDto editarProductoPorId(@Param("idProducto") Long idProducto);

    @Procedure(procedureName = "actualizar_stock_precio")
    void actualizar_stock_precio(
            @Param("p_id_producto") Long idProducto,
            @Param("p_cantidad_stock") Integer cantidadStock,
            @Param("p_ultimo_precio_unitario") Double ultimoPrecioUnitario,
            @Param("p_ultimo_precio_venta") Double ultimoPrecioVenta
    );

}
