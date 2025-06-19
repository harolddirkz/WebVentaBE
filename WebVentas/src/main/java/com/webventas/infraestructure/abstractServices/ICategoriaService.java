package com.webventas.infraestructure.abstractServices;

import com.webventas.domain.dto.request.CategoriaRequest;
import com.webventas.domain.dto.request.CategoriaUpdate;
import com.webventas.domain.entities.Categoria;

import java.util.List;


public interface ICategoriaService {

    Categoria create(CategoriaRequest categoria);
    void deleteCategoria(Long id);
    List<Categoria> findAllCategorias();
    void editCategoria(CategoriaUpdate categoria);
}
