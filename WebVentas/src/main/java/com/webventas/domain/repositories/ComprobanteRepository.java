package com.webventas.domain.repositories;

import com.webventas.domain.entities.Comprobante;
import com.webventas.utils.TipoComprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante, Long> {

    @Query(value = """ 
    SELECT MAX(c.numeroComprobante) 
    FROM Comprobantes c 
    WHERE c.tipoComprobante = :tipo
    """, nativeQuery = true)
    Integer encontrarUltimoNumeroPorTipo(@Param("tipo") String tipo);

    @Query(value = """ 
    SELECT DISTINCT c.serieComprobante
    FROM Comprobantes c 
    WHERE c.tipoComprobante = :tipo
    """, nativeQuery = true)
    Optional<String> encontrarSeriePorTipo(@Param("tipo") String tipo);
}
