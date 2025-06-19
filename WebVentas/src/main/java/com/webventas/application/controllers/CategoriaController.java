package com.webventas.application.controllers;

import com.webventas.domain.dto.request.CategoriaRequest;
import com.webventas.domain.dto.request.CategoriaUpdate;
import com.webventas.domain.entities.Categoria;
import com.webventas.infraestructure.abstractServices.ICategoriaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@AllArgsConstructor
public class CategoriaController {

    private final ICategoriaService categoriaService;

    @PostMapping(value = "/create")
    public ResponseEntity<Categoria> createCategoria(@Valid @RequestBody CategoriaRequest request) {
        return ResponseEntity.ok(categoriaService.create(request));
    }

    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteCategoria(id);
    }

    @GetMapping("/list")
    public List<Categoria> listAllCategories() {
        return categoriaService.findAllCategorias();
    }

    @PutMapping("/edit")
    public void editCategoria(@RequestBody CategoriaUpdate request) {
        categoriaService.editCategoria(request);

    }

}
