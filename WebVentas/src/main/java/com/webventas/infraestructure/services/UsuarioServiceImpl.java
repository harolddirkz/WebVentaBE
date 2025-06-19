package com.webventas.infraestructure.services;

import com.webventas.domain.dto.request.ActualizarUsuarioRequest;
import com.webventas.domain.dto.request.UsuarioRequest;
import com.webventas.domain.entities.Usuario;
import com.webventas.domain.repositories.UsuarioRepository;
import com.webventas.infraestructure.abstractServices.IUsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

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
}
