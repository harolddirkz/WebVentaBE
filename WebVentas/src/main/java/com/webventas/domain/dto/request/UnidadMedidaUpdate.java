package com.webventas.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnidadMedidaUpdate {
    private int idUnidadMedida;
    private String nombreUnidadMedida;
    private String abreviatura;
}
