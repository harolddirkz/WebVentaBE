package com.webventas.application.controllers;

import com.webventas.domain.dto.request.UbicacionRequest;
import com.webventas.domain.entities.Ubicacion;
import com.webventas.infraestructure.abstractServices.IUbicacionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ubicacion")
@AllArgsConstructor
public class UbicacionController {

    private final IUbicacionService ubicacionService;

    @PostMapping(value = "/create")
    public ResponseEntity<Ubicacion> createUbicacion(@Valid @RequestBody UbicacionRequest request) {
        return ResponseEntity.ok(ubicacionService.create(request));

    }

    @DeleteMapping("/{id}")
    public void deleteUbicacion(@PathVariable Long id) {
        ubicacionService.deleteUbicacion(id);
    }

    @GetMapping("/list")
    public List<Ubicacion> listAllUbicaciones() {
        return ubicacionService.findAllUbicaciones();
    }
}
