package com.example.Cines.Snacks.Infraestructura.Input.Rest.Models;

import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.Snacks.Dominio.TipoSnacks;
import lombok.Value;

import java.util.UUID;

@Value
public class ResponseSnacksDTO {
    private UUID id;
    private String nombre;
    private Double precio;
    private TipoSnacks tipo;
    private Cine idCine;
    private Double promedio_valoracion;
}
