package com.webventas.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Proveedores")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProveedor")
    private Long idProveedor;

    @Column(name = "NombreProveedor")
    private String nombreProveedor;

    @Column(name = "Contacto")
    private String contacto;

    @Column(name = "Direccion")
    private String direccion;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="proveedor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Compra> compras;

    @ManyToMany(fetch=FetchType.LAZY, mappedBy="proveedores", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Producto> productos;
}
