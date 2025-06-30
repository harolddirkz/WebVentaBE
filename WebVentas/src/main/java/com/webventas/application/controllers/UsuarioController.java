package com.webventas.application.controllers;

import com.webventas.domain.dto.request.ActualizarUsuarioRequest;
import com.webventas.domain.dto.request.UsuarioRequest;
import com.webventas.domain.entities.Usuario;
import com.webventas.infraestructure.abstractServices.IUsuarioService;
import com.webventas.security.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
public class UsuarioController {

    private final IUsuarioService usuarioService;

    @PostMapping(value = "/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody UsuarioRequest request) {
        return ResponseEntity.ok(usuarioService.crearUsuario(request));

    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Usuario>> getAllUsuarios(@RequestParam(required = false) Boolean habilitado) {
        List<Usuario> usuarios = usuarioService.getAllUsuarios(habilitado);
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/actualizar")
    @PreAuthorize("hasRole('ADMIN')")
    public void actualizarUsuario(@RequestBody ActualizarUsuarioRequest request) {
        usuarioService.updateUsuario(request);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>("No autenticado.", HttpStatus.UNAUTHORIZED);
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) principal;

            java.util.Map<String, Object> response = new java.util.HashMap<>();
            response.put("idUsuario", userDetails.getIdUsuario());
            response.put("nombre", userDetails.getNombre());
            response.put("username", userDetails.getUsername());
            response.put("rol", userDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", ""));

            return new ResponseEntity<>(response, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Tipo de principal inesperado o no autenticado correctamente.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
