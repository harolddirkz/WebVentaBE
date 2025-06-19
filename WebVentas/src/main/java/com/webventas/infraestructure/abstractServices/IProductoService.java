package com.webventas.infraestructure.abstractServices;

import com.webventas.domain.dto.request.ActualizarProductoRequest;
import com.webventas.domain.dto.request.ActualizarStockPrecio;
import com.webventas.domain.dto.request.ProductoRequest;
import com.webventas.domain.dto.response.*;
import com.webventas.domain.entities.Producto;

import java.util.List;

public interface IProductoService {
    void create(ProductoRequest request);
    void deleteProducto(Long id);
    List<ProductResponseDto> findAllProductos();
    void actualizarProducto(ActualizarProductoRequest request);
    void actualizarMiProducto(ActualizarStockPrecio request);
    void activarProducto(Long id);
    void desactivarProducto(Long id);
    List<Producto> listarProductosInactivos();
    List<MyProductsResponseDto> findMyProducts();
    List<ProductResponseDto> findProductsWithState();
    List<ProductosEstadoStockDto> findProductsWithStock();
    EditarProductoPorIdDto searchProductoPorId(Long idProducto);
    EditarMiProductoPorIdDto searchMiProductoPorId(Long idProducto);
}
