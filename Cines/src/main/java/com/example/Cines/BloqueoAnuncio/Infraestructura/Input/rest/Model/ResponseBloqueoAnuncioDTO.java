package com.example.Cines.BloqueoAnuncio.Infraestructura.Input.rest.Model;

import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class ResponseBloqueoAnuncioDTO {
    private UUID id;
    private LocalDate fecha;
    private LocalDate fecha_fin;
    private Long cantidad_dias;
    private UUID idCine;
}
