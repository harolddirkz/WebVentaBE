package com.webventas.application.controllers;

import com.webventas.domain.dto.request.AuthRequest;
import com.webventas.domain.entities.Usuario;
import com.webventas.infraestructure.services.UsuarioServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UsuarioServiceImpl usuarioService; // Asumiendo que inyectas tu servicio de usuario

    public AuthController(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        Usuario usuario = usuarioService.findByUsuario(authRequest.getUsuario()); // Busca por nombre de usuario

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado.");
        }

        // Verifica la contraseña encriptada
        if (usuarioService.verificarContrasena(authRequest.getContrasena(), usuario.getContrasenaHash())) {
            // Login exitoso
            // Aquí podrías generar un token JWT o simplemente devolver un mensaje de éxito
            // Para una aplicación interna simple, podrías devolver el rol y el nombre del usuario
            // y manejar la sesión en el frontend con el rol
            return ResponseEntity.ok("Login exitoso. Rol: " + usuario.getRol()); // Ejemplo simple
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas.");
        }
    }
}
