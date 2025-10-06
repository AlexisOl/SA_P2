package com.example.Cines.Cine.Infraestructura.Input.Rest.Models.Response;

import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class ResponseCineDTO {
    private UUID id;
    private String nombre;
    private String ubicacion;
    private String telefono;
    private LocalDate fechaCreacion;
}
