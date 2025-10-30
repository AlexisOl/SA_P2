package com.example.Cines.Snacks.Aplicacion.CasoUso.CrearSnacks;

import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.Snacks.Dominio.TipoSnacks;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearSnacksDTO {
    private String nombre;
    private Double precio;
    private String  tipo;
    private UUID idCine;
}
