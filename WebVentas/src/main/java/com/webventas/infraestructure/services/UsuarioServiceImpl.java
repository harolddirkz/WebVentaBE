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

    public List<Usuario> getAllUsuarios(Boolean habilitado) {
        List<Usuario> usuarios;
        if (habilitado != null) {
            usuarios = usuarioRepository.findByHabilitado(habilitado); // Necesitarás este método en el Repository
        } else {
            usuarios = usuarioRepository.findAll(); // Si habilitado es null, trae todos
        }
        // Opcional: Si no quieres enviar la contraseña hasheada al frontend
        return usuarios.stream()
                .map(usuario -> {
                    // Crea una nueva instancia para evitar modificar el objeto persistido si se vuelve a usar
                    Usuario safeUser = new Usuario();
                    safeUser.setIdUsuario(usuario.getIdUsuario());
                    safeUser.setNombre(usuario.getNombre());
                    safeUser.setUsuario(usuario.getUsuario());
                    safeUser.setRol(usuario.getRol());
                    safeUser.setEmail(usuario.getEmail());
                    safeUser.setHabilitado(usuario.isHabilitado());
                    safeUser.setFechaCreacion(usuario.getFechaCreacion());
                    safeUser.setUltimaConexion(usuario.getUltimaConexion());
                    // NO copiar contrasena ni contrasenaHash
                    return safeUser;
                })
                .collect(Collectors.toList());
    }

    @Transactional // Agrega @Transactional si tu lógica lo requiere para operaciones de DB
    public void updateUsuario(ActualizarUsuarioRequest request) {
        Usuario usuarioExistente = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + request.getIdUsuario()));

        // Actualizar campos que siempre se pueden cambiar
        usuarioExistente.setNombre(request.getNombre());
        usuarioExistente.setRol(request.getRol());
        usuarioExistente.setUsuario(request.getUsuario()); // Considera si el usuario (username) debería ser editable
        usuarioExistente.setEmail(request.getEmail());
        usuarioExistente.setHabilitado(request.isHabilitado());

        // **CLAVE: Hashear la contraseña SOLO si se proporciona una nueva contraseña**
        // Si request.getContrasena() NO es nulo y NO está vacío, significa que el frontend envió una nueva contraseña
        if (request.getContrasena() != null && !request.getContrasena().isEmpty()) {
            String hashedPassword = passwordEncoder.encode(request.getContrasena());
            usuarioExistente.setContrasena(hashedPassword); // Guardar el hash, no la contraseña plana
        }
        // Si request.getContrasena() es nulo o vacío, no hacemos nada, conservando la contraseña hasheada existente

        usuarioRepository.saveAndFlush(usuarioExistente);
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
