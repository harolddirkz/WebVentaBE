package com.webventas.application.controllers;

import com.webventas.domain.dto.request.RegistrarVentaRequest;
import com.webventas.domain.dto.response.VentaResponseDto;
import com.webventas.infraestructure.abstractServices.IVentaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/sales")
@AllArgsConstructor
public class VentaController {

    private final IVentaService ventaService;

    @PostMapping("/register")
    public void realizarVenta(@RequestBody RegistrarVentaRequest request) {
        ventaService.registrarVenta(request);
    }

    @GetMapping("/report/{fecha}")
    public VentaResponseDto todayReport(@PathVariable String fecha) {
        return ventaService.ventaPorFecha(fecha);
    }

    @GetMapping("/report/rango")
    public VentaResponseDto reporteEntreFechas(
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {
        return ventaService.ventaEntreFechas(fechaInicio, fechaFin);
    }
}
