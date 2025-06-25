package com.webventas.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
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

    @Column(name = "Nombre", length = 100)
    private String nombre;

    @Column(name = "Rol", length = 50)
    private String rol;

    @Column(name = "Usuario", unique = true)
    private String usuario;

    @Column(name = "Contrasena")
    private String contrasena;

    @Column(nullable = false)
    private String contrasenaHash; // Almacenaremos el hash aquí

    @Column(nullable = false, unique = true, length = 100)
    private String email; // Para recuperación de contraseña

    @Column(nullable = false)
    private boolean habilitado = true; // Por defecto activo

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    private LocalDateTime ultimaConexion;


    @OneToMany(fetch=FetchType.LAZY, mappedBy="usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Compra> compras;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Venta> ventas;

}
