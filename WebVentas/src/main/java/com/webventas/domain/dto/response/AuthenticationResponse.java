package com.webventas.domain.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String jwt;
    private final List<String> roles;

    public AuthenticationResponse(String jwt, List<String> roles) {
        this.jwt = jwt;
        this.roles = roles;
    }

}
