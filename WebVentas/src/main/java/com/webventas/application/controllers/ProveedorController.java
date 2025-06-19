package com.webventas.application.controllers;

import com.webventas.domain.dto.request.ActualizarProductoRequest;
import com.webventas.domain.dto.request.ActualizarProveedorRequest;
import com.webventas.domain.dto.request.ProveedorRequest;
import com.webventas.domain.entities.Cliente;
import com.webventas.domain.entities.Producto;
import com.webventas.domain.entities.Proveedor;
import com.webventas.infraestructure.abstractServices.IProveedorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedor")
@AllArgsConstructor
public class ProveedorController {

    private final IProveedorService proveedorService;

    @PostMapping(value = "/create")
    public ResponseEntity<Proveedor> createProveedor(@Valid @RequestBody ProveedorRequest request) {
        return ResponseEntity.ok(proveedorService.create(request));

    }

    @DeleteMapping("/{id}")
    public void deleteProveedor(@PathVariable Long id) {
        proveedorService.deleteProveedor(id);
    }

    @GetMapping("/list")
    public List<Proveedor> listAllProveedores() {
        return proveedorService.findAllProveedores();
    }

    @GetMapping("/buscar")
    public List<Proveedor> buscarProveedores(@RequestParam(required = false) String query) {
        return proveedorService.buscarProveedor(query);
    }

    @PutMapping("/actualizar")
    public void actualizarProveedor(@RequestBody ActualizarProveedorRequest request) {
        proveedorService.updateProveedor(request);
    }
}
