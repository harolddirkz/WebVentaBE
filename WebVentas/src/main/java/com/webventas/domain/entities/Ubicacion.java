package com.webventas.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Ubicaciones")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUbicacion")
    private Long idUbicacion;

    @Column(name = "CodigoUbicacion")
    private String codigoUbicacion;

    @Column(name = "DescripcionUbicacion")
    private String descripcionUbicacion;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="ubicacion", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Stock> stocks;

}
