package com.example.Cines.Cine.Infraestructura.Kafka.DTO.EstadoMonetarioAnuncioComprado;

import lombok.Data;

import java.util.UUID;

@Data
public class CineActualizadoDTO {
    private UUID anuncioId;
    private String correlationId;
}
