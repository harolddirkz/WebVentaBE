package com.webventas.application.controllers;

import com.webventas.domain.dto.request.ActualizarProductoRequest;
import com.webventas.domain.dto.request.ActualizarProveedorRequest;
import com.webventas.domain.dto.request.ProveedorRequest;
import com.webventas.domain.entities.Cliente;
import com.webventas.domain.entities.Producto;
import com.webventas.domain.entities.Proveedor;
import com.webventas.infraestructure.abstractServices.IProveedorService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void deleteProveedor(@PathVariable Long id) {
        proveedorService.deleteProveedor(id);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    public List<Proveedor> listAllProveedores() {
        return proveedorService.findAllProveedores();
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    public List<Proveedor> buscarProveedores(@RequestParam(required = false) String query) {
        return proveedorService.buscarProveedor(query);
    }

    @PutMapping("/actualizar")
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    public void actualizarProveedor(@RequestBody ActualizarProveedorRequest request) {
        proveedorService.updateProveedor(request);
    }

    @GetMapping(value = "/create-proveedor-rapido")
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    public ResponseEntity<Proveedor> createProveedorRapido(@RequestParam String ruc) {
        return ResponseEntity.ok(proveedorService.crearProveedorFast(ruc));

    }
}
