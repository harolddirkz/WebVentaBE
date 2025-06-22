package com.webventas.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiRucResponseDto {
    private String ruc;
    private String razonSocial;
    private String nombreComercial;
    private String direccion;
    private String telefono;
}
