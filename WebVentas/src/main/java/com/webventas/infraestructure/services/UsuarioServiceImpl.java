package com.webventas.infraestructure.services;

import com.webventas.domain.dto.request.ActualizarUsuarioRequest;
import com.webventas.domain.dto.request.UsuarioRequest;
import com.webventas.domain.entities.Usuario;
import com.webventas.domain.repositories.UsuarioRepository;
import com.webventas.infraestructure.abstractServices.IUsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> getAllUsuarios(Boolean habilitado) {
        List<Usuario> usuarios;
        if (habilitado != null) {
            usuarios = usuarioRepository.findByHabilitado(habilitado);
        } else {
            usuarios = usuarioRepository.findAll();
        }

        return usuarios.stream()
                .map(usuario -> {
                    Usuario safeUser = new Usuario();
                    safeUser.setIdUsuario(usuario.getIdUsuario());
                    safeUser.setNombre(usuario.getNombre());
                    safeUser.setUsuario(usuario.getUsuario());
                    safeUser.setRol(usuario.getRol());
                    safeUser.setEmail(usuario.getEmail());
                    safeUser.setHabilitado(usuario.isHabilitado());
                    safeUser.setFechaCreacion(usuario.getFechaCreacion());
                    safeUser.setUltimaConexion(usuario.getUltimaConexion());
                    return safeUser;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUsuario(ActualizarUsuarioRequest request) {
        Usuario usuarioExistente = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + request.getIdUsuario()));

        usuarioExistente.setNombre(request.getNombre());
        usuarioExistente.setRol(request.getRol());
        usuarioExistente.setUsuario(request.getUsuario());
        usuarioExistente.setEmail(request.getEmail());
        usuarioExistente.setHabilitado(request.isHabilitado());

        if (request.getContrasena() != null && !request.getContrasena().isEmpty()) {
            String hashedPassword = passwordEncoder.encode(request.getContrasena());
            usuarioExistente.setContrasena(hashedPassword);
        }


        usuarioRepository.saveAndFlush(usuarioExistente);
    }

    @Override
    public Usuario crearUsuario(UsuarioRequest request) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(request.getNombre());
        nuevoUsuario.setUsuario(request.getUsuario());
        nuevoUsuario.setEmail(request.getEmail());
        nuevoUsuario.setRol(request.getRol());
        nuevoUsuario.setFechaCreacion(LocalDateTime.now());
        nuevoUsuario.setContrasenaHash(passwordEncoder.encode(request.getContrasena()));
        return usuarioRepository.save(nuevoUsuario);
    }

    @Override
    public Usuario findByUsuario(String usuario) {
        Usuario user = usuarioRepository.findByUsuario(usuario).orElse(null);
        if (usuario != null) {
            return user;
        }
        return user;
    }

    public boolean verificarContrasena(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
