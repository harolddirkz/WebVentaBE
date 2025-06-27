package com.webventas.application.controllers;

import com.webventas.domain.dto.request.ActualizarClienteRequest;
import com.webventas.domain.dto.request.ActualizarProductoRequest;
import com.webventas.domain.dto.request.ClienteRequest;
import com.webventas.domain.dto.request.RequestClienteRapido;
import com.webventas.domain.entities.Categoria;
import com.webventas.domain.entities.Cliente;
import com.webventas.domain.entities.Producto;
import com.webventas.infraestructure.abstractServices.IClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@AllArgsConstructor
public class ClienteController {

    private final IClienteService clienteService;

    @PostMapping(value = "/create")
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody ClienteRequest request) {
        return ResponseEntity.ok(clienteService.create(request));
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
    }

    @GetMapping("/list")
    public List<Cliente> listAllClientes() {
        return clienteService.findAllClientes();
    }

    @GetMapping("/buscar")
    public List<Cliente> buscarClientes(@RequestParam(required = false) String query) {
        return clienteService.buscarCliente(query);
    }

    @PutMapping("/actualizar")
    public void actualizarCliente(@RequestBody ActualizarClienteRequest request) {
        clienteService.updateCliente(request);
    }

    @PostMapping(value = "/create-rapido")
    public ResponseEntity<Cliente> createClienteRapido(@Valid @RequestBody RequestClienteRapido request) {
        return ResponseEntity.ok(clienteService.createClienteFast(request));

    }


}
