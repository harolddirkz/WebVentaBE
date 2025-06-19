package com.webventas.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Compras")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCompra")
    private Long idCompra;

    @Column(name = "FechaRecepcion")
    private Date fechaRecepcion;

    @Column(name = "TipoComprobante")
    private String tipoComprobante;

    @Column(name = "NumeroComprobante")
    private String numeroComprobante;

    @Column(name = "Total")
    private Double total;

    @Column(name = "FechaCompra")
    private Date fechaCompra;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "IdUsuario")
    private Usuario usuario;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "idProveedor")
    private Proveedor proveedor;

}
