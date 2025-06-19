package com.webventas.infraestructure.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webventas.domain.dto.request.RegistrarVentaRequest;
import com.webventas.domain.dto.response.VentaResponseBdDto;
import com.webventas.domain.dto.response.VentaResponseDto;
import com.webventas.domain.repositories.VentaRepository;
import com.webventas.infraestructure.abstractServices.IVentaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaServiceImpl implements IVentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @Transactional
    public void registrarVenta(RegistrarVentaRequest venta) {
        try {
            String productosJson = objectMapper.writeValueAsString(venta.getDetalles());
            String sql = "CALL registrar_venta(?, ?, ?::DECIMAL, ?::TEXT, ?::TEXT, ?::TEXT, ?::DATE, ?::TEXT, ?::TEXT)";
            jdbcTemplate.update(
                    sql,
                    Integer.parseInt(String.valueOf(venta.getIdCliente())),
                    Integer.parseInt(String.valueOf(venta.getIdUsuario())),
                    venta.getTotal(),
                    venta.getTipoComprobante(),
                    venta.getSerieComprobante(),
                    venta.getNumeroComprobante(),
                    venta.getFechaVenta(),
                    venta.getMetodoPago(),
                    productosJson
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public VentaResponseDto ventaPorFecha(String fecha) {

        List<VentaResponseBdDto> ventaBD = ventaRepository.obtenerVentasPorFecha(fecha);

        double totalPrecioUnitario = ventaBD.stream()
                .mapToDouble(VentaResponseBdDto::getPrecioUnitario)
                .sum();

        double totalPrecioVenta = ventaBD.stream()
                .filter(v -> !"SERVICIO MECANICO".equalsIgnoreCase(v.getNombreProducto()))
                .mapToDouble(VentaResponseBdDto::getPrecioVenta)
                .sum();

        double totalUtilidad = ventaBD.stream()
                .mapToDouble(VentaResponseBdDto::getUtilidad)
                .sum();

        double totalGeneral = ventaBD.stream()
                .collect(Collectors.groupingBy(VentaResponseBdDto::getIdVenta))
                .values().stream()
                .mapToDouble(lista -> lista.get(0).getTotal())
                .sum();

        VentaResponseDto respuesta = new VentaResponseDto();
        respuesta.setSumaTotalPrecioUnitario(totalPrecioUnitario);
        respuesta.setSumaTotalPrecioVenta(totalPrecioVenta);
        respuesta.setSumaTotalUtilidad(totalUtilidad);
        respuesta.setSumaTotalDeTotales(totalGeneral);
        respuesta.setVentas(ventaBD);

        return respuesta;
    }

    @Override
    public VentaResponseDto ventaEntreFechas(String fechaInicio, String fechaFin) {
        List<VentaResponseBdDto> ventaBD = ventaRepository.obtenerVentasEntreFechas(fechaInicio, fechaFin);
        double totalPrecioUnitario = ventaBD.stream()
                .mapToDouble(VentaResponseBdDto::getPrecioUnitario)
                .sum();

        double totalPrecioVenta = ventaBD.stream()
                .filter(v -> !"SERVICIO TECNICO".equalsIgnoreCase(v.getNombreProducto()))
                .mapToDouble(VentaResponseBdDto::getPrecioVenta)
                .sum();

        double totalUtilidad = ventaBD.stream()
                .mapToDouble(VentaResponseBdDto::getUtilidad)
                .sum();

        double totalGeneral = ventaBD.stream()
                .collect(Collectors.groupingBy(VentaResponseBdDto::getIdVenta))
                .values().stream()
                .mapToDouble(list -> list.get(0).getTotal())
                .sum();

        VentaResponseDto dto = new VentaResponseDto();
        dto.setVentas(ventaBD);
        dto.setSumaTotalPrecioUnitario(totalPrecioUnitario);
        dto.setSumaTotalPrecioVenta(totalPrecioVenta);
        dto.setSumaTotalUtilidad(totalUtilidad);
        dto.setSumaTotalDeTotales(totalGeneral);

        return dto;
    }
}
