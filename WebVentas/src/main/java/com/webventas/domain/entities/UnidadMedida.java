package com.webventas.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "UnidadesMedida")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UnidadMedida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUnidadMedida")
    private Long idUnidadMedida;

    @Column(name = "NombreUnidadMedida")
    private String nombreUnidadMedida;

    @Column(name = "Abreviatura")
    private String abreviatura;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="unidadMedida", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Producto> productos;

}
