package com.webventas.infraestructure.services;

import com.webventas.domain.entities.Usuario;
import com.webventas.domain.repositories.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority; // Importar esto

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca el usuario por su nombre de usuario (el campo 'usuario' en tu entidad)
        Usuario usuario = usuarioRepository.findByUsuario(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Construye un objeto UserDetails de Spring Security
        // Asumiendo que tu entidad Usuario tiene un campo 'rol' (String)
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
        // Asegúrate de que el rol se almacene con el prefijo "ROLE_" en Spring Security

        return new org.springframework.security.core.userdetails.User(
                usuario.getUsuario(), // Username de Spring Security
                usuario.getContrasenaHash(), // Contraseña encriptada
                authorities // Roles/Autoridades
        );
    }
}
