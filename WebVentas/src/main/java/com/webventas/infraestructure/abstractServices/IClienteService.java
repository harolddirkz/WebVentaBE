package com.webventas.infraestructure.abstractServices;

import com.webventas.domain.dto.request.ActualizarClienteRequest;
import com.webventas.domain.dto.request.ClienteRequest;
import com.webventas.domain.dto.request.RequestClienteRapido;
import com.webventas.domain.entities.Cliente;

import java.util.List;

public interface IClienteService {

    Cliente create(ClienteRequest request);
    void deleteCliente(Long id);
    List<Cliente> findAllClientes();
    void updateCliente(ActualizarClienteRequest request);
    List<Cliente> buscarCliente(String query);
    Cliente createFast(RequestClienteRapido request);


}
