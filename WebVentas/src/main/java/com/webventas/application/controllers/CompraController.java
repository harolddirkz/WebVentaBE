package com.webventas.application.controllers;

import com.webventas.domain.dto.request.RegistrarCompraRequest;
import com.webventas.infraestructure.abstractServices.ICompraService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buys")
@AllArgsConstructor
public class CompraController {

    private final ICompraService compraService;

    @PostMapping("/register")
    public void realizarCompra(@RequestBody RegistrarCompraRequest request) {
        compraService.registrarCompra(request);
    }
}
