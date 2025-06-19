package com.webventas.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ProductoProveedor")
public class ProductoProveedor {

    @Id
    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    @Id
    @ManyToOne
    @JoinColumn(name = "idProveedor")
    private Proveedor proveedor;
}
