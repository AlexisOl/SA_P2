package com.example.Cines.Cine.Aplicacion.CasoUso.CrearCine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearCineDTO {
    private String nombre;
    private String ubicacion;
    private String telefono;
    private Double costo;
    private Double costoOcultacion;
    private Double cartera;
}
