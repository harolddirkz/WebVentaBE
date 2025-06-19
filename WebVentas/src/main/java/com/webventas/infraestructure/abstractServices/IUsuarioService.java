package com.webventas.infraestructure.abstractServices;

import com.webventas.domain.dto.request.ActualizarUsuarioRequest;
import com.webventas.domain.dto.request.UsuarioRequest;
import com.webventas.domain.entities.Usuario;

import java.util.List;

public interface IUsuarioService {

    Usuario create (UsuarioRequest request);
    void deleteUsuario(Long id);
    List<Usuario> findAllUsuarios();
    void updateUsuario(ActualizarUsuarioRequest request);

}
