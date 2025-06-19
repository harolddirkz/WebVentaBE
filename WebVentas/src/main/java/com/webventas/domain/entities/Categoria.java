package com.webventas.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Categorias")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCategoria")
    private Long idCategoria;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Descripcion")
    private String descripcion;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="categoria", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Producto> productos;
}
