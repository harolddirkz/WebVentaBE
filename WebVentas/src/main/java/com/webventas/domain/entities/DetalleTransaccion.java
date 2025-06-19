package com.webventas.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

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
    private Double precioUnitario;

    @Column(name = "PrecioVenta")
    private Double precioVenta;

    @Column(name = "Importe")
    private Double importe;

    @Column(name = "Utilidad")
    private Double utilidad;

    @Column(name = "IdReferencia")
    private int idReferencia;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "IdProducto")
    private Producto producto;

    @Column(name = "TipoTransaccion")
    private String tipoTransaccion;

}
