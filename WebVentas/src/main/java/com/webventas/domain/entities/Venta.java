package com.webventas.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Ventas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdVenta")
    private Long idVenta;

    @Column(name = "FechaVenta")
    private Date fechaVenta;

    @Column(name = "TipoComprobante")
    private String tipoComprobante;

    @Column(name = "NumeroComprobante")
    private String numeroComprobante;

    @Column(name = "Total")
    private Double total;

    @Column(name = "MetodoPago")
    private String metodoPago;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "IdUsuario")
    private Usuario usuario;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "idComprobante")
    private Comprobante comprobante;

}
