package com.example.Cines.Cine.Aplicacion.CasoUso.CrearCine;

import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class CrearCineDTO {
    private String nombre;
    private String ubicacion;
    private String telefono;
}
