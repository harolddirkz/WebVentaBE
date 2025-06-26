package com.webventas.infraestructure.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webventas.domain.dto.request.ActualizarProductoRequest;
import com.webventas.domain.dto.request.ActualizarStockPrecio;
import com.webventas.domain.dto.request.ProductoRequest;
import com.webventas.domain.dto.response.*;
import com.webventas.domain.entities.Categoria;
import com.webventas.domain.entities.Producto;
import com.webventas.utils.Constants;
import com.webventas.domain.entities.UnidadMedida;
import com.webventas.domain.repositories.CategoriaRepository;
import com.webventas.domain.repositories.ProductoRepository;
import com.webventas.domain.repositories.UnidadMedidaRepository;
import com.webventas.infraestructure.abstractServices.IProductoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public void create(ProductoRequest request) {
        UnidadMedida unidadMedida = unidadMedidaRepository.findById(request.getIdUnidadMedida())
                .orElseThrow(() -> new RuntimeException("Unidad de medida no encontrada"));
        Categoria categoria = categoriaRepository.findById(request.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Producto producto = new Producto();
        producto.setNombreProducto(request.getNombreProducto());
        producto.setMarca(request.getMarca());
        producto.setDescripcion(request.getDescripcion());
        producto.setPresentacion(request.getPresentacion());
        producto.setImagenUrl(request.getImagenUrl());
        producto.setFechaActivo(request.getFechaActivo());
        producto.setEstado(request.getEstado());
        producto.setUnidadMedida(unidadMedida);
        producto.setCategoria(categoria);
        productoRepository.saveAndFlush(producto);
    }

    @Override
    public void deleteProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
        productoRepository.deleteById(id);
    }

    @Override
    public List<ProductResponseDto> findAllProductos() {
        List<Producto> productos = productoRepository.findAll(Sort.by(Sort.Direction.ASC, "idProducto"));
        return productos.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MyProductsResponseDto> findMyProducts() {
        List<Producto> product = productoRepository.findAll();
        return product.stream()
                .map(this::convertirDtoResponse)
                .collect(Collectors.toList());

    }

    @Override
    public List<ProductResponseDto> findProductsWithState() {
        List<Producto> producto = productoRepository.listarProductosActivos();
        return producto.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductosEstadoStockDto> findProductsWithStock() {
        return productoRepository.listarProductosConStock();
    }

    @Override
    public EditarProductoPorIdDto searchProductoPorId(Long idProducto) {
        return productoRepository.editarProductoPorId(idProducto);

    }

    @Override
    public EditarMiProductoPorIdDto searchMiProductoPorId(Long idProducto) {
        return productoRepository.editarMiProductoPorId(idProducto);

    }

    private ProductResponseDto convertirADTO(Producto producto) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setIdProducto(producto.getIdProducto());
        dto.setNombreProducto(producto.getNombreProducto());
        dto.setMarca(producto.getMarca());
        dto.setDescripcion(producto.getDescripcion());
        dto.setEstado(producto.getEstado());
        dto.setPresentacion(producto.getPresentacion());
        dto.setImagenUrl(producto.getImagenUrl());
        dto.setUltimoPrecioUnitario(producto.getUltimoPrecioUnitario() == null ? 0.0 : producto.getUltimoPrecioUnitario());
        dto.setUltimoPrecioVenta(producto.getUltimoPrecioVenta() == null ? 0.0 : producto.getUltimoPrecioVenta());

        return dto;
    }

    private MyProductsResponseDto convertirDtoResponse(Producto producto) {
        MyProductsResponseDto myProduct = new MyProductsResponseDto();
        myProduct.setNombreProducto(producto.getNombreProducto());
        myProduct.setMarca(producto.getMarca());
        myProduct.setDescripcion(producto.getDescripcion());
        myProduct.setFechaVencimiento(producto.getFechaVencimiento());
        myProduct.setEstado(producto.getEstado());
        myProduct.setPresentacion(producto.getPresentacion());
        myProduct.setImagenUrl(producto.getImagenUrl());
        return myProduct;
    }

    @Override
    public void actualizarProducto(ActualizarProductoRequest request) {
        Producto producto = productoRepository.findById(request.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + request.getIdProducto()));

        producto.setNombreProducto(request.getNombreProducto());
        producto.setMarca(request.getMarca());
        producto.setDescripcion(request.getDescripcion());
        producto.setPresentacion(request.getPresentacion());
        producto.setImagenUrl(request.getImagenUrl());

        if (request.getIdUnidadMedida() != null) {
            UnidadMedida unidadMedida = unidadMedidaRepository.findById(request.getIdUnidadMedida())
                    .orElseThrow(() -> new RuntimeException("Unidad de medida no encontrada con ID: " + request.getIdUnidadMedida()));
            producto.setUnidadMedida(unidadMedida);
        }

        if (request.getIdCategoria() != null) {
            Categoria categoria = categoriaRepository.findById(request.getIdCategoria())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + request.getIdCategoria()));
            producto.setCategoria(categoria);
        }
        producto.setEstado(request.getEstado());

        if(request.getEstado().equals(Constants.ESTADO_ACTIVO)) {
            producto.setFechaActivo(request.getFechaActivo());
        } else {
            producto.setFechaInactivo(request.getFechaInactivo());
        }
        productoRepository.saveAndFlush(producto);
    }

    @Override
    @Transactional
    public void actualizarMiProducto(ActualizarStockPrecio request) {
        String sql = "CALL actualizar_stock_precio(?, ?, ?, ?)";
        try {
            jdbcTemplate.update(
                    sql,
                    request.getIdProducto(),
                    request.getCantidadStock(),
                    request.getUltimoPrecioUnitario(),
                    request.getUltimoPrecioVenta()
            );
        } catch (DataAccessException e) {

            System.out.println("Error al actualizar el producto: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void activarProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        LocalDate fechaActual = LocalDate.now();

        producto.setEstado("ACTIVO");
        producto.setFechaActivo(java.sql.Date.valueOf(fechaActual));

        productoRepository.saveAndFlush(producto);
    }

    @Override
    public void desactivarProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        LocalDate fechaActual = LocalDate.now();

        producto.setEstado("INACTIVO");
        producto.setFechaActivo(java.sql.Date.valueOf(fechaActual));
        productoRepository.saveAndFlush(producto);

    }

    @Override
    public List<Producto> listarProductosInactivos() {
        return productoRepository.listarProductosInactivos();
    }
}
