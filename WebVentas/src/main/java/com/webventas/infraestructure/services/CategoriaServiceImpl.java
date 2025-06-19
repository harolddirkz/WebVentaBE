package com.webventas.infraestructure.services;

import com.webventas.domain.dto.request.CategoriaRequest;
import com.webventas.domain.dto.request.CategoriaUpdate;
import com.webventas.domain.entities.Categoria;
import com.webventas.domain.repositories.CategoriaRepository;
import com.webventas.infraestructure.abstractServices.ICategoriaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria create(CategoriaRequest categoria) {
        Categoria categoriaEntity = new Categoria();
        categoriaEntity.setNombre(categoria.getNombre());
        categoriaEntity.setDescripcion(categoria.getDescripcion());
        return categoriaRepository.saveAndFlush(categoriaEntity);
    }

    @Transactional
    @Override
    public void deleteCategoria(Long id) {

        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoría no encontrada con id: " + id);
        }
        categoriaRepository.deleteById(id);
    }

    @Override
    public List<Categoria> findAllCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public void editCategoria(CategoriaUpdate request) {
        Categoria categoria = categoriaRepository.findById(Long.valueOf(request.getIdCategoria()))
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + request.getIdCategoria()));
        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());
        categoriaRepository.saveAndFlush(categoria);
    }

}
