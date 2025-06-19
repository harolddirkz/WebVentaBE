package com.webventas.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Usuarios")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUsuario")
    private Long idUsuario;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Rol")
    private String rol;

    @Column(name = "Usuario")
    private String usuario;

    @Column(name = "Contrasena")
    private String contrasena;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Compra> compras;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Venta> ventas;

}
