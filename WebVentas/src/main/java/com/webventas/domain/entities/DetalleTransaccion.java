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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdProducto", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdVentaRef", referencedColumnName = "IdVenta", nullable = true)
    private Venta venta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCompraRef", referencedColumnName = "IdCompra", nullable = true)
    private Compra compra;

    @PrePersist @PreUpdate
    public void validarReferencias() {
        if ("VENTA".equals(this.tipoTransaccion)) {
            if (this.venta == null || this.compra != null) {
                throw new IllegalStateException("Detalle de Venta debe tener referencia a Venta y no a Compra.");
            }
        } else if ("COMPRA".equals(this.tipoTransaccion)) {
            if (this.compra == null || this.venta != null) {
                throw new IllegalStateException("Detalle de Compra debe tener referencia a Compra y no a Venta.");
            }
        } else {
            throw new IllegalStateException("Tipo de Transacción inválido.");
        }
    }


}
