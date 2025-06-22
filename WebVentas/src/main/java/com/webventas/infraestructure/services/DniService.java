package com.webventas.infraestructure.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webventas.domain.dto.response.ApiRucResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DniService {

    @Value("${api.peru.token}")
    private String apiToken;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public DniService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public String obtenerNombreCompleto(String dni) {
        String url = "https://dniruc.apisperu.com/api/v1/dni/" + dni + "?token=" + apiToken;

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                JsonNode jsonNode = objectMapper.readTree(response.getBody());

                if (jsonNode.path("success").asBoolean()) {
                    String nombres = jsonNode.path("nombres").asText();
                    String apellidoPaterno = jsonNode.path("apellidoPaterno").asText();
                    String apellidoMaterno = jsonNode.path("apellidoMaterno").asText();

                    return nombres + " " + apellidoPaterno + " " + apellidoMaterno;
                } else {
                    throw new RuntimeException("Error al consultar el DNI: Respuesta no exitosa");
                }
            } else {
                throw new RuntimeException("Error al consultar el DNI: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al consumir el API", e);
        }
    }

    public ApiRucResponseDto obtenerDatosPorRuc(String ruc) {
        String url = "https://dniruc.apisperu.com/api/v1/ruc/" + ruc + "?token=" + apiToken;
        System.out.println("url: " + url);

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new IllegalStateException("Respuesta HTTP inválida: " + response.getStatusCode());
            }

            JsonNode json = objectMapper.readTree(response.getBody());

            return new ApiRucResponseDto(
                    json.path("ruc").asText(),
                    json.path("razonSocial").asText(),
                    json.path("nombreComercial").asText(),
                    json.path("direccion").asText(),
                    json.path("telefono").asText()
            );

        } catch (Exception e) {
            throw new RuntimeException("Error al consumir el API", e);
        }
    }


}