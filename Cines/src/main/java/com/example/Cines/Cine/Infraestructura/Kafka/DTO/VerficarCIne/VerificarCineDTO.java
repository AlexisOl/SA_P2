package com.example.Cines.Cine.Infraestructura.Kafka.DTO.VerficarCIne;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class VerificarCineDTO {
    private UUID idCine;
    private String correlationId;

    public VerificarCineDTO(UUID idCine, String correlationId) {
        this.idCine = idCine;
        this.correlationId = correlationId;
    }
    public VerificarCineDTO() {}

    public UUID getIdCine() {
        return idCine;
    }

    public void setIdCine(UUID idCine) {
        this.idCine = idCine;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

}
