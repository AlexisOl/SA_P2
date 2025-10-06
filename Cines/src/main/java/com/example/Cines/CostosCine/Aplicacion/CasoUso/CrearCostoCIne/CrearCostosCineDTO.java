package com.example.Cines.CostosCine.Aplicacion.CasoUso.CrearCostoCIne;

import com.example.Cines.Cine.Dominio.Cine;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class CrearCostosCineDTO {
    private LocalDate fecha;
    private Double costo;
    private UUID idCine;
}
