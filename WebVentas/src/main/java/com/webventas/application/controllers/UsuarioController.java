package com.webventas.application.controllers;

import com.webventas.domain.dto.request.ActualizarClienteRequest;
import com.webventas.domain.dto.request.ActualizarUsuarioRequest;
import com.webventas.domain.dto.request.UsuarioRequest;
import com.webventas.domain.entities.UnidadMedida;
import com.webventas.domain.entities.Usuario;
import com.webventas.infraestructure.abstractServices.IUsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
public class UsuarioController {

    private final IUsuarioService usuarioService;

    @PostMapping(value = "/create")
    public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody UsuarioRequest request) {
        return ResponseEntity.ok(usuarioService.crearUsuario(request));

    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Usuario>> getAllUsuarios(@RequestParam(required = false) Boolean habilitado) {
        List<Usuario> usuarios = usuarioService.getAllUsuarios(habilitado);
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/actualizar")
    public void actualizarUsuario(@RequestBody ActualizarUsuarioRequest request) {
        usuarioService.updateUsuario(request);
    }
}
