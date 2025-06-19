package com.webventas.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Clientes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCliente")
    private Long idCliente;

    @Column(name = "TipoDocumento")
    private String tipoDocumento;

    @Column(name = "NumeroDocumento")
    private String numeroDocumento;

    @Column(name = "NombreCliente")
    private String nombreCliente;

    @Column(name = "Contacto")
    private String contacto;

    @Column(name = "Direccion")
    private String direccion;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="cliente", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Venta> ventas;

}
