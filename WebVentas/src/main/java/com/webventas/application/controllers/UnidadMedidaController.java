package com.webventas.application.controllers;

import com.webventas.domain.dto.request.UnidadMedidaRequest;
import com.webventas.domain.dto.request.UnidadMedidaUpdate;
import com.webventas.domain.entities.Ubicacion;
import com.webventas.domain.entities.UnidadMedida;
import com.webventas.infraestructure.abstractServices.IUnidadMedidaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidad-medida")
@AllArgsConstructor
public class UnidadMedidaController {

    private final IUnidadMedidaService unidadService;

    @PostMapping(value = "/create")
    public ResponseEntity<UnidadMedida> createUnidadMedida(@Valid @RequestBody UnidadMedidaRequest request) {
        return ResponseEntity.ok(unidadService.create(request));

    }

    @DeleteMapping("/{id}")
    public void deleteUnidadMedida(@PathVariable Long id) {
        unidadService.deleteUnidadMedidad(id);
    }

    @GetMapping("/list")
    public List<UnidadMedida> listAllUnidadMedida() {
        return unidadService.findAllUnidadMedidas();
    }

    @PutMapping("/edit")
    public void updateUnidadMedida(@Valid @RequestBody UnidadMedidaUpdate request) {
        unidadService.editUnidadMedida(request);
    }
}
