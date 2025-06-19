package com.webventas.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Productos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProducto")
    private Long idProducto;

    @Column(name = "NombreProducto")
    private String nombreProducto;

    @Column(name = "Marca")
    private String marca;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "FechaVencimiento")
    private Date fechaVencimiento;

    @Column(name = "FechaActivo")
    private Date fechaActivo;

    @Column(name = "FechaInactivo")
    private Date fechaInactivo;

    @Column(name = "Estado")
    private String estado;

    @Column(name = "CodigoBarras")
    private String codigoBarras;

    @Column(name = "Presentacion")
    private String presentacion;

    @Column(name = "ImagenUrl")
    private String imagenUrl;

    @Column(name = "UltimoPrecioUnitario")
    private Double ultimoPrecioUnitario;

    @Column(name = "UltimoPrecioVenta")
    private Double ultimoPrecioVenta;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "idUnidadMedida")
    private UnidadMedida unidadMedida;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="producto", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DetalleTransaccion> detalleTransaccion;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="producto", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Stock> stocks;

    @ManyToMany
    @JoinTable(
            name = "ProductoProveedor",
            joinColumns = @JoinColumn(name = "idProducto"),
            inverseJoinColumns = @JoinColumn(name = "idProveedor")
    )
    private List<Proveedor> proveedores;

}
