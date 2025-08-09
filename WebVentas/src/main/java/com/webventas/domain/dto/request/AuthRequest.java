package com.webventas.domain.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String usuario;
    private String contrasena;

}
