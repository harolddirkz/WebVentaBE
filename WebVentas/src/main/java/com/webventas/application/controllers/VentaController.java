package com.webventas.application.controllers;

import com.webventas.domain.dto.request.RegistrarVentaRequest;
import com.webventas.domain.dto.response.VentaResponseDto;
import com.webventas.domain.entities.Venta;
import com.webventas.infraestructure.abstractServices.IVentaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

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
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    public VentaResponseDto todayReport(@PathVariable String fecha) {
        return ventaService.ventaPorFecha(fecha);
    }

    @GetMapping("/report/rango")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public VentaResponseDto reporteEntreFechas(
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {
        return ventaService.ventaEntreFechas(fechaInicio, fechaFin);
    }

    @PostMapping("/registrar")
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    public ResponseEntity<Venta> registrarVenta(@RequestBody RegistrarVentaRequest ventaDto) {
        try {
            Venta nuevaVenta = ventaService.registrarNuevaVenta(ventaDto);
            return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            System.err.println("Error en el controlador al registrar venta: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
