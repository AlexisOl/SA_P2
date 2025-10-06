package com.example.Cines.Snacks.Aplicacion.CasoUso.CrearSnacks;

import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.Snacks.Dominio.TipoSnacks;
import lombok.Value;

import java.util.UUID;

@Value
public class CrearSnacksDTO {
    private String nombre;
    private Double precio;
    private String  tipo;
    private UUID idCine;
}
