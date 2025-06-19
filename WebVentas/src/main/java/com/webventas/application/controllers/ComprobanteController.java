package com.webventas.application.controllers;

import com.webventas.domain.dto.response.ComprobanteResponseDto;
import com.webventas.infraestructure.services.ComprobanteServiceImpl;
import com.webventas.utils.TipoComprobante;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comprobante")
@AllArgsConstructor
public class ComprobanteController {

    @Autowired
    private ComprobanteServiceImpl comprobanteServiceImpl;

    @GetMapping("/proximo")
    public ResponseEntity<ComprobanteResponseDto> obtenerProximo(@RequestParam String tipo) {
        ComprobanteResponseDto dto = comprobanteServiceImpl.obtenerNumeracion(tipo);
        return ResponseEntity.ok(dto);
    }

}
