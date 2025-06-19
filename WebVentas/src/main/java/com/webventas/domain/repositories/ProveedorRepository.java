package com.webventas.domain.repositories;

import com.webventas.domain.entities.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Long> {
    @Query(value = """
    SELECT c.* FROM Proveedores p
    WHERE 
        (:query IS NULL OR 
         p.NombreProveedor ILIKE CONCAT('%', :query, '%') OR 
         c.Contacto ILIKE CONCAT('%', :query, '%')
    """, nativeQuery = true)
    List<Proveedor> buscarProveedor(@Param("query") String query);
}
