package com.webventas.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Stock")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdStock")
    private Long idStock;

    @Column(name = "CantidadStock")
    private int cantidadStock;

    @Column(name = "StockMinimo")
    private int stockMinimo;

    @Column(name = "StockMaximo")
    private int stockMaximo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "idProducto")
    private Producto producto;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "idUbicacion")
    private Ubicacion ubicacion;

}
