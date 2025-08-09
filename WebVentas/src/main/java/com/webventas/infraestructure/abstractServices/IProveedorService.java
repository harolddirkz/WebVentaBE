package com.webventas.infraestructure.abstractServices;

import com.webventas.domain.dto.request.ActualizarProveedorRequest;
import com.webventas.domain.dto.request.ProveedorRequest;
import com.webventas.domain.entities.Proveedor;

import java.util.List;

public interface IProveedorService {
    Proveedor create(ProveedorRequest request);
    void deleteProveedor(Long id);
    List<Proveedor> findAllProveedores();
    void updateProveedor(ActualizarProveedorRequest request);
    Proveedor crearProveedorFast(String ruc);

}
