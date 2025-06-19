package com.webventas.domain.repositories;

import com.webventas.domain.entities.UnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida,Long> {
}
