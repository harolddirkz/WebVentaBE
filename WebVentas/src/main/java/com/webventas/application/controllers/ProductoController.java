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

    //funciona
    @PostMapping("/create")
    public void crearProducto(@RequestBody ProductoRequest productoRequest) {
        productoService.create(productoRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
    }

    //funciona
    @GetMapping("/list")
    public List<ProductResponseDto> findAllProductosDTO() {
        return productoService.findAllProductos();
    }

    //funciona
    @GetMapping("/list-by-state")
    public List<ProductResponseDto> findProductsByState() {
        return productoService.findProductsWithState();
    }

    //funciona
    @GetMapping("/list-by-stock")
    public List<ProductosEstadoStockDto> findMyProductsByStock() {
        return productoService.findProductsWithStock();
    }

    //funciona
    @GetMapping("/editar/{idProducto}")
    public EditarProductoPorIdDto editarProducto(@PathVariable Long idProducto) {
        return productoService.searchProductoPorId(idProducto);
    }

    //funciona
    @GetMapping("/editar/mi-producto/{idProducto}")
    public EditarMiProductoPorIdDto editarMiProducto(@PathVariable Long idProducto) {
        return productoService.searchMiProductoPorId(idProducto);
    }

    //funciona
    @PutMapping("/actualizar")
    public void actualizarProducto(@RequestBody ActualizarProductoRequest request) {
        productoService.actualizarProducto(request);
    }

    //funciona
    @PutMapping("/actualizar/mi-producto")
    public void actualizarMiProducto(@RequestBody ActualizarStockPrecio request) {
        productoService.actualizarMiProducto(request);
    }

    @GetMapping("/listarInactivos")
    public List<Producto> listarProductosInactivos() {
        return productoService.listarProductosInactivos();
    }

}
