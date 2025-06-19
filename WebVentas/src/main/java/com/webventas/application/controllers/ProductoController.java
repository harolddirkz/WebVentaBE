package com.webventas.application.controllers;

import com.webventas.domain.dto.request.ActualizarProductoRequest;
import com.webventas.domain.dto.request.ActualizarStockPrecio;
import com.webventas.domain.dto.request.ProductoRequest;
import com.webventas.domain.dto.response.*;
import com.webventas.domain.entities.Producto;
import com.webventas.infraestructure.abstractServices.IProductoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
@AllArgsConstructor
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @PostMapping("/create")
    public void crearProducto(@RequestBody ProductoRequest productoRequest) {
        productoService.create(productoRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
    }

    @GetMapping("/list")
    public List<ProductResponseDto> findAllProductosDTO() {
        return productoService.findAllProductos();
    }

    @GetMapping("/list-by-state")
    public List<ProductResponseDto> findProductsByState() {
        return productoService.findProductsWithState();
    }

    @GetMapping("/list-my-products")
    public List<MyProductsResponseDto> findMyProductos() {
        return productoService.findMyProducts();
    }

    @GetMapping("/list-by-stock")
    public List<ProductosEstadoStockDto> findMyProductsByStock() {
        return productoService.findProductsWithStock();
    }

    @GetMapping("/editar/{idProducto}")
    public EditarProductoPorIdDto editarProducto(@PathVariable Long idProducto) {
        return productoService.searchProductoPorId(idProducto);
    }

    @GetMapping("/editar/mi-producto/{idProducto}")
    public EditarMiProductoPorIdDto editarMiProducto(@PathVariable Long idProducto) {
        return productoService.searchMiProductoPorId(idProducto);
    }

    @PutMapping("/actualizar")
    public void actualizarProducto(@RequestBody ActualizarProductoRequest request) {
        productoService.actualizarProducto(request);
    }

    @PutMapping("/actualizar/mi-producto")
    public void actualizarMiProducto(@RequestBody ActualizarStockPrecio request) {
        productoService.actualizarMiProducto(request);
    }

    @PutMapping("/desactivar/{id}")
    public void desactivarProducto(@PathVariable Long id) {
        productoService.desactivarProducto(id);
    }

    @PutMapping("/activar/{id}")
    public void activarProducto(@PathVariable Long id) {
        productoService.activarProducto(id);
    }

    @GetMapping("/listarInactivos")
    public List<Producto> listarProductosInactivos() {
        return productoService.listarProductosInactivos();
    }

}
