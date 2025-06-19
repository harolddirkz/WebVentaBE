package com.webventas.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyProductsResponseDto extends ProductResponseDto{

    private String imagenUrl;
    private Date fechaVencimiento;
}
