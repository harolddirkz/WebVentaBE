package com.webventas.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webventas.utils.TipoComprobante;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Comprobantes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdComprobante")
    private Long idComprobante;

    @Column(name = "TipoComprobante")
    @Enumerated(EnumType.STRING)
    private TipoComprobante tipoComprobante;

    @Column(name = "NumeroComprobante")
    private Integer numeroComprobante;

    @Column(name = "SerieComprobante")
    private String serieComprobante;

    @Column(name = "FechaEmision")
    private Date fechaEmision;

    @OneToOne(fetch=FetchType.LAZY, mappedBy = "comprobante",cascade = CascadeType.ALL)
    @JsonIgnore
    private Venta venta;

}
