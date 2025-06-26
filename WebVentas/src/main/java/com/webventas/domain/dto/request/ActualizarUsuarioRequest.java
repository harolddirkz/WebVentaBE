package com.webventas.domain.dto.request;

import lombok.Data;

@Data
public class ActualizarUsuarioRequest {
    private Long idUsuario;
    private String nombre;
    private String rol;
    private String usuario;
    private String contrasena;
    private String email;
    private boolean habilitado;
}
