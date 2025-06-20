package com.webventas.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "DetalleTransacciones")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DetalleTransaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDetalleTransaccion")
    private Long idDetalleTransaccion;

    @Column(name = "Cantidad")
    private int cantidad;

    @Column(name = "PrecioUnitario")
    private BigDecimal precioUnitario;

    @Column(name = "PrecioVenta")
    private BigDecimal precioVenta;

    @Column(name = "Importe")
    private BigDecimal importe;

    @Column(name = "Utilidad")
    private BigDecimal utilidad;

    @Column(name = "TipoTransaccion")
    private String tipoTransaccion;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "IdProducto")
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdReferencia", referencedColumnName = "IdVenta", nullable = false)
    private Venta venta;

}
