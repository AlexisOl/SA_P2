package com.example.Cines.BloqueoAnuncio.Aplicacion.CasosUso.CrearBloqueoAnuncios;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CrearBloqueoAnuncioDTO {
    private LocalDate fecha;
    private Long cantidad_dias;
    private UUID cine;
}
