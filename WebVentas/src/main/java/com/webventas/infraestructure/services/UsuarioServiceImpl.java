package com.webventas.infraestructure.services;

import com.webventas.domain.dto.request.ActualizarUsuarioRequest;
import com.webventas.domain.dto.request.UsuarioRequest;
import com.webventas.domain.entities.Usuario;
import com.webventas.domain.repositories.UsuarioRepository;
import com.webventas.infraestructure.abstractServices.IUsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario create(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setUsuario(request.getUsuario());
        usuario.setRol(request.getRol());
        usuario.setContrasena(request.getContrasena());
        return usuarioRepository.saveAndFlush(usuario);
    }

    @Transactional
    @Override
    public void deleteUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public void updateUsuario(ActualizarUsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + request.getIdUsuario()));

        usuario.setNombre(request.getNombre());
        usuario.setUsuario(request.getUsuario());
        usuario.setRol(request.getRol());
        usuario.setContrasena(request.getContrasena());
        usuarioRepository.saveAndFlush(usuario);

    }

    @Override
    public Usuario crearUsuario(UsuarioRequest request) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(request.getNombre());
        nuevoUsuario.setUsuario(request.getUsuario());
        nuevoUsuario.setEmail(request.getEmail()); // Asegúrate de pedir el email en el request
        nuevoUsuario.setRol(request.getRol());
        nuevoUsuario.setFechaCreacion(LocalDateTime.now());
        // ¡ENCRIPTAR LA CONTRASEÑA ANTES DE GUARDAR!
        nuevoUsuario.setContrasenaHash(passwordEncoder.encode(request.getContrasena()));
        return usuarioRepository.save(nuevoUsuario);
    }

    @Override
    public Usuario findByUsuario(String usuario) {
        Usuario user = usuarioRepository.findByUsuario(usuario);
        if (usuario != null) {
            return user;
        }
        return user;
    }

    public boolean verificarContrasena(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
