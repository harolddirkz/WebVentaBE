package com.webventas.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaUpdate {
    private int idCategoria;
    private String nombre;
    public String descripcion;
}
