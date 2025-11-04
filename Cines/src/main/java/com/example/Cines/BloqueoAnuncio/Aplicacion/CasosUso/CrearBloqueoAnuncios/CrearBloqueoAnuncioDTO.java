package com.example.Cines.BloqueoAnuncio.Aplicacion.CasosUso.CrearBloqueoAnuncios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearBloqueoAnuncioDTO {
    private LocalDate fecha;
    private Long cantidad_dias;
    private UUID cine;
}
