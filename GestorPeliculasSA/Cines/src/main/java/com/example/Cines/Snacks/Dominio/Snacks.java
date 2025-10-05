package com.example.Cines.Snacks.Dominio;

import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.Salas.Dominio.TipoSala;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Snacks {
    private UUID id;
    private String nombre;
    private Double precio;
    private TipoSnacks  tipo;
    private Cine idCine;
    private Double promedio_valoracion;

    public Snacks(UUID id, String nombre, Double precio, TipoSnacks tipo, Cine idCine, Double promedio_valoracion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.idCine = idCine;
        this.promedio_valoracion = promedio_valoracion;
    }

    public Snacks(UUID id, String nombre, Double precio, TipoSnacks tipo, Cine idCine) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.idCine = idCine;
        this.promedio_valoracion = 0.0;
    }

    public Snacks() {}

}
