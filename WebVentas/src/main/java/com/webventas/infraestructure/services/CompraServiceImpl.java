package com.webventas.infraestructure.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webventas.domain.dto.request.RegistrarCompraRequest;
import com.webventas.domain.repositories.CompraRepository;
import com.webventas.infraestructure.abstractServices.ICompraService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CompraServiceImpl implements ICompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @Transactional
    public void registrarCompra(RegistrarCompraRequest compra) {
        try {
            String productosJson = objectMapper.writeValueAsString(compra.getDetalles());
            String sql = "CALL sp_registrar_compra(?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(
                    sql,
                    compra.getIdProveedor().intValue(),
                    compra.getIdUsuario().intValue(),
                    compra.getTipoComprobante(),
                    compra.getNumeroComprobante(),
                    compra.getTotal(),
                    productosJson
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al registrar la compra: " + e.getMessage(), e);
        }

    }
}
