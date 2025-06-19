package com.webventas.infraestructure.abstractServices;

import com.webventas.domain.dto.request.RegistrarCompraRequest;

public interface ICompraService {

    void registrarCompra(RegistrarCompraRequest compra);
}
