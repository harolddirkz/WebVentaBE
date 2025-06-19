package com.webventas.domain.repositories;

import com.webventas.domain.entities.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra,Long> {
    @Procedure(procedureName = "registrar_compra")
    void registrar_compra(
            @Param("p_IdProveedor") Long idProveedor,
            @Param("p_IdUsuario") Long idUsuario,
            @Param("p_tipoComprobante") String tipoComprobante,
            @Param("p_numeroComprobante") String numeroComprobante,
            @Param("p_productos") String productosJson
    );
}
