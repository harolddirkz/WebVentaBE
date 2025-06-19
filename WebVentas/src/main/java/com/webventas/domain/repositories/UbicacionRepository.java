package com.webventas.domain.repositories;

import com.webventas.domain.entities.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion,Long> {
}
