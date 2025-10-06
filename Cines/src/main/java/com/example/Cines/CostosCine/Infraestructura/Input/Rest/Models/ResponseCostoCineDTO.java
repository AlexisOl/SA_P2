package com.example.Cines.CostosCine.Infraestructura.Input.Rest.Models;

import com.example.Cines.Cine.Dominio.Cine;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class ResponseCostoCineDTO {
    private UUID id;
    private LocalDate fecha;
    private Double costo;
    private Cine idCine;
}
