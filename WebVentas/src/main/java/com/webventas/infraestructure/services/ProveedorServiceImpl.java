package com.webventas.infraestructure.services;

import com.webventas.domain.dto.request.ActualizarProveedorRequest;
import com.webventas.domain.dto.request.ProveedorRequest;
import com.webventas.domain.entities.Proveedor;
import com.webventas.domain.repositories.ProveedorRepository;
import com.webventas.infraestructure.abstractServices.IProveedorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webventas.domain.dto.response.ApiRucResponseDto;

import java.util.List;

@Service
public class ProveedorServiceImpl implements IProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private  DniService dniService;

    @Override
    public Proveedor create(ProveedorRequest request) {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombreComercial(request.getNombreComercial());
        proveedor.setRuc(request.getRuc());
        proveedor.setRazonSocial(request.getRazonSocial());
        proveedor.setTelefono(request.getTelefono());
        proveedor.setDireccion(request.getDireccion());
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
        proveedor.setNombreComercial(request.getNombreProveedor());
        proveedor.setDireccion(request.getDireccion());
        proveedor.setTelefono(request.getContacto());

        proveedorRepository.saveAndFlush(proveedor);
    }

    @Override
    public List<Proveedor> buscarProveedor(String query) {
        return proveedorRepository.buscarProveedor(query);
    }

    @Override
    public Proveedor crearProveedorFast(String ruc) {

        ApiRucResponseDto respuesta = dniService.obtenerDatosPorRuc(ruc);
        Proveedor proveedor = new Proveedor();
        proveedor.setNombreComercial(respuesta.getNombreComercial());
        proveedor.setRuc(respuesta.getRuc());
        proveedor.setRazonSocial(respuesta.getRazonSocial());
        proveedor.setDireccion(respuesta.getDireccion());
        proveedor.setTelefono(respuesta.getTelefono());

        return proveedorRepository.saveAndFlush(proveedor);

    }

}
