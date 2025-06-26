package com.webventas.application.controllers;

import com.webventas.domain.dto.request.AuthRequest; // Your DTO for login request
import com.webventas.domain.dto.response.AuthenticationResponse;
import com.webventas.infraestructure.services.CustomUserDetailsService;
import com.webventas.utils.JwtUtil; // Your JWT utility class

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager; // Import this
import org.springframework.security.authentication.BadCredentialsException; // Import this
import org.springframework.security.authentication.DisabledException; // Import this
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; // Import this
import org.springframework.security.core.userdetails.UserDetails; // Import this
import org.springframework.security.core.GrantedAuthority; // Import this
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors; // Import this

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // Keep CORS
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService; // Tu UserDetailsService
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager,
                          CustomUserDetailsService userDetailsService,
                          JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsuario(), authenticationRequest.getContrasena())
            );
        } catch (DisabledException e) {
            throw new Exception("USUARIO_DESHABILITADO", e);
        } catch (BadCredentialsException e) {
            throw new Exception("CREDENCIALES_INVALIDAS", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsuario());
        final String jwt = jwtUtil.generateToken(userDetails);

        // Puedes devolver un objeto con el token y cualquier otra información necesaria (ej. roles)
        return ResponseEntity.ok(new AuthenticationResponse(jwt, userDetails.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority().replace("ROLE_", "")).collect(Collectors.toList())));
    }
}