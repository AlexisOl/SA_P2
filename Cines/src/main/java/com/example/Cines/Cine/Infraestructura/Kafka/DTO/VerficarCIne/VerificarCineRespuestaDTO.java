package com.example.Cines.Cine.Infraestructura.Kafka.DTO.VerficarCIne;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

public class VerificarCineRespuestaDTO {
    private boolean existe;
    private String correlationId;
    public VerificarCineRespuestaDTO(boolean existe, String correlationId) {
        this.existe = existe;
        this.correlationId = correlationId;
    }

    public VerificarCineRespuestaDTO() {}

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}
