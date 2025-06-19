package com.webventas.domain.repositories;

import com.webventas.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long>  {

    @Query(value = """
    SELECT c.* FROM Clientes c
    WHERE 
        (:query IS NULL OR 
         c.NumeroDocumento ILIKE CONCAT('%', :query, '%') OR 
         c.NombreCliente ILIKE CONCAT('%', :query, '%') OR 
         c.Contacto ILIKE CONCAT('%', :query, '%')
    """, nativeQuery = true)
    List<Cliente> buscarCliente(@Param("query") String query);
}
