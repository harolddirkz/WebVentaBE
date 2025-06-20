package com.webventas.infraestructure.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webventas.domain.dto.request.RegistrarVentaRequest;
import com.webventas.domain.dto.response.VentaResponseBdDto;
import com.webventas.domain.dto.response.VentaResponseDto;
import com.webventas.domain.entities.Cliente;
import com.webventas.domain.entities.Comprobante;
import com.webventas.domain.entities.Usuario;
import com.webventas.domain.entities.Venta;
import com.webventas.domain.repositories.ClienteRepository;
import com.webventas.domain.repositories.ComprobanteRepository;
import com.webventas.domain.repositories.UsuarioRepository;
import com.webventas.domain.repositories.VentaRepository;
import com.webventas.infraestructure.abstractServices.IVentaService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class VentaServiceImpl implements IVentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ComprobanteRepository comprobanteRepository;


    @PersistenceContext
    private EntityManager entityManager;

    private final ObjectMapper objectMapper = new ObjectMapper();


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

        BigDecimal totalPrecioUnitario = ventaBD.stream()
                .map(VentaResponseBdDto::getPrecioVenta)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPrecioVenta = ventaBD.stream()
                .filter(v -> !"SERVICIO MECANICO".equalsIgnoreCase(v.getNombreProducto()))
                .map(VentaResponseBdDto::getPrecioVenta)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalUtilidad = ventaBD.stream()
                .map(VentaResponseBdDto::getUtilidad)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalGeneral = ventaBD.stream()
                .collect(Collectors.groupingBy(VentaResponseBdDto::getIdVenta))
                .values().stream()
                .map(lista -> lista.get(0).getTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

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

        BigDecimal totalPrecioUnitario = ventaBD.stream()
                .map(VentaResponseBdDto::getPrecioUnitario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPrecioVenta = ventaBD.stream()
                .filter(v -> !"SERVICIO MECANICO".equalsIgnoreCase(v.getNombreProducto()))
                .map(VentaResponseBdDto::getPrecioVenta)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        BigDecimal totalUtilidad = ventaBD.stream()
                .map(VentaResponseBdDto::getUtilidad)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalGeneral = ventaBD.stream()
                .collect(Collectors.groupingBy(VentaResponseBdDto::getIdVenta))
                .values().stream()
                .map(list -> list.get(0).getTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        VentaResponseDto dto = new VentaResponseDto();
        dto.setVentas(ventaBD);
        dto.setSumaTotalPrecioUnitario(totalPrecioUnitario);
        dto.setSumaTotalPrecioVenta(totalPrecioVenta);
        dto.setSumaTotalUtilidad(totalUtilidad);
        dto.setSumaTotalDeTotales(totalGeneral);

        return dto;
    }

    @Override
    @Transactional
    public Venta registrarNuevaVenta(RegistrarVentaRequest requests) {
        Comprobante comprobante = new Comprobante();
        comprobante.setTipoComprobante(requests.getTipoComprobante());
        comprobante.setSerieComprobante(requests.getSerieComprobante());
        comprobante.setNumeroComprobante(requests.getNumeroComprobante());
        comprobante.setFechaEmision(requests.getFechaVenta() != null ? requests.getFechaVenta() : new Date());

        Venta venta = new Venta();
        venta.setTotal(requests.getTotal());
        venta.setFechaVenta(requests.getFechaVenta() != null ? requests.getFechaVenta() : new Date());
        venta.setMetodoPago(requests.getMetodoPago());
        venta.setFechaVenta(requests.getFechaVenta() != null ? requests.getFechaVenta() : new Date());
        System.out.println("fecha venta: " + venta.getFechaVenta());

        Cliente cliente = clienteRepository.findById(requests.getIdCliente())
                .orElseThrow(() -> new NoSuchElementException("Cliente con ID " + requests.getIdCliente() + " no encontrado."));
        venta.setCliente(cliente);

        Usuario usuario = usuarioRepository.findById(requests.getIdUsuario())
                .orElseThrow(() -> new NoSuchElementException("Usuario con ID " + requests.getIdUsuario() + " no encontrado."));
        venta.setUsuario(usuario);

        venta.setComprobante(comprobante);
        comprobante.setVenta(venta);

        venta = ventaRepository.save(venta);

        try {
            String detallesJson = objectMapper.writeValueAsString(requests.getDetalles());

            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("sp_registrar_venta");
            storedProcedure.registerStoredProcedureParameter("p_IdVenta", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_productos", String.class, ParameterMode.IN);

            storedProcedure.setParameter("p_IdVenta", venta.getIdVenta());
            storedProcedure.setParameter("p_productos", detallesJson);

            storedProcedure.execute();

        } catch (Exception e) {
            System.err.println("Error al ejecutar el SP registrar_venta: " + e.getMessage());
            throw new RuntimeException("Error al registrar detalles de venta o actualizar stock.", e);
        }

        return venta;
    }
}
