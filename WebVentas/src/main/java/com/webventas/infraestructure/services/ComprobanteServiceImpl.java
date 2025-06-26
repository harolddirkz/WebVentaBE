package com.webventas.infraestructure.services;

import com.webventas.domain.dto.response.ComprobanteResponseDto;
import com.webventas.domain.repositories.ComprobanteRepository;
import com.webventas.infraestructure.abstractServices.IComprobanteService;
import com.webventas.utils.TipoComprobante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComprobanteServiceImpl implements IComprobanteService {

    @Autowired
    private ComprobanteRepository comprobanteRepository;

    @Override
    public ComprobanteResponseDto obtenerNumeracion(String tipo) {
        TipoComprobante tipoEnum;
        try {
            tipoEnum = TipoComprobante.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException e) {

            tipoEnum = TipoComprobante.DESCONOCIDO;
        }

        TipoComprobante finalTipoEnum = tipoEnum;
        String serie = comprobanteRepository.encontrarSeriePorTipo(tipo)
                .orElseGet(() -> {
                    switch (finalTipoEnum) {
                        case BOLETA_DE_VENTA: return "B001";
                        case FACTURA: return "F001";
                        case NOTA_DE_VENTA: return "NV001";
                        case TICKET: return "T001";
                        case GUIA_DE_REMISION: return "G001";
                        case RECIBO: return "R001";
                        default: return "S001";
                    }
                });

        Integer ultimoNumero = comprobanteRepository.encontrarUltimoNumeroPorTipo(tipoEnum.name());
        int proximoNumero = (ultimoNumero != null ? ultimoNumero + 1 : 1);

        String resp = serie + "-" + proximoNumero;

        return new ComprobanteResponseDto(resp);
    }
}
