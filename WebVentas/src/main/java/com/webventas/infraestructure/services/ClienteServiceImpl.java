package com.webventas.infraestructure.services;

import com.webventas.domain.dto.request.ActualizarClienteRequest;
import com.webventas.domain.dto.request.ClienteRequest;
import com.webventas.domain.dto.request.RequestClienteRapido;
import com.webventas.domain.entities.Cliente;
import com.webventas.domain.repositories.ClienteRepository;
import com.webventas.infraestructure.abstractServices.IClienteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private  DniService dniService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente create(ClienteRequest request) {
        Cliente cliente = new Cliente();
        cliente.setNombreCliente(request.getNombreCliente());
        cliente.setContacto(request.getContacto());
        cliente.setDireccion(request.getDireccion());
        cliente.setTipoDocumento(request.getTipoDocumento());
        cliente.setNumeroDocumento(request.getNumeroDocumento());
        return clienteRepository.saveAndFlush(cliente);
    }

    @Transactional
    @Override
    public void deleteCliente(Long id) {

        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        }

        clienteRepository.deleteById(id);
    }

    @Override
    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public void updateCliente(ActualizarClienteRequest request) {
        Cliente cliente = clienteRepository.findById(request.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + request.getIdCliente()));

        cliente.setNumeroDocumento(request.getNumeroDocumento());
        cliente.setTipoDocumento(request.getTipoDocumento());
        cliente.setNombreCliente(request.getNombreCliente());
        cliente.setContacto(request.getContacto());
        cliente.setDireccion(request.getDireccion());

        clienteRepository.saveAndFlush(cliente);
    }

    @Override
    public List<Cliente> buscarCliente(String query) {
        return clienteRepository.buscarCliente(query);
    }

    @Override
    public Cliente createClienteFast(RequestClienteRapido request) {
        String nombreCompleto = dniService.obtenerNombreCompleto(request.getNumeroDocumento());

        Cliente cliente = new Cliente();
        cliente.setNombreCliente(nombreCompleto);
        cliente.setContacto(request.getContacto());
        cliente.setTipoDocumento(request.getTipoDocumento());
        cliente.setNumeroDocumento(request.getNumeroDocumento());
        return clienteRepository.saveAndFlush(cliente);

    }



}
