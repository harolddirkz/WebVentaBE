package com.webventas.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class UsuarioRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Es obligatorio ingresar el nombre")
    private String nombre;
    @NotBlank(message = "Es obligatorio ingresar el nombre del rol")
    private String rol;
    @NotBlank(message = "El nombre de usuario no puede estar en blanco o vacio")
    private String usuario;
    @NotBlank(message = "Es obligatorio ingresar una contraseña")
    private String contrasena;
}
