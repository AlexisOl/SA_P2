package com.example.Cines.Salas.Infraestructura.Input.Rest.Models;

import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.Salas.Dominio.ObjetosValor.MatrizAsientos;
import com.example.Cines.Salas.Dominio.ObjetosValor.ValidacionesSala;
import com.example.Cines.Salas.Dominio.TipoSala;
import lombok.Value;

import java.util.UUID;

@Value
public class ResponseSalasDTO {
    private UUID id;
    private String nombre;
    private TipoSala tipoSala;
    private MatrizAsientos matrizAsientos;
    private ValidacionesSala validacionesSala;
    private Cine idCine;
    private Double promedio_valoracion;
}
