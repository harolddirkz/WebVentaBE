package com.webventas.infraestructure.services;

import com.webventas.domain.dto.request.ActualizarProveedorRequest;
import com.webventas.domain.dto.request.ProveedorRequest;
import com.webventas.domain.entities.Proveedor;
import com.webventas.domain.repositories.ProveedorRepository;
import com.webventas.infraestructure.abstractServices.IProveedorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImpl implements IProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public Proveedor create(ProveedorRequest request) {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombreProveedor(request.getNombreProveedor());
        proveedor.setDireccion(request.getDireccion());
        proveedor.setContacto(request.getContacto());
        return proveedorRepository.saveAndFlush(proveedor);
    }

    @Transactional
    @Override
    public void deleteProveedor(Long id) {
        if (!proveedorRepository.existsById(id)) {
            throw new RuntimeException("Proveedor no encontrado con id: " + id);
        }
        proveedorRepository.deleteById(id);
    }

    @Override
    public List<Proveedor> findAllProveedores() {
        return proveedorRepository.findAll();
    }

    @Override
    public void updateProveedor(ActualizarProveedorRequest request) {
        Proveedor proveedor = proveedorRepository.findById(request.getIdProveedor())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + request.getIdProveedor()));
        proveedor.setNombreProveedor(request.getNombreProveedor());
        proveedor.setDireccion(request.getDireccion());
        proveedor.setContacto(request.getContacto());

        proveedorRepository.saveAndFlush(proveedor);
    }

    @Override
    public List<Proveedor> buscarProveedor(String query) {
        return proveedorRepository.buscarProveedor(query);
    }
}
