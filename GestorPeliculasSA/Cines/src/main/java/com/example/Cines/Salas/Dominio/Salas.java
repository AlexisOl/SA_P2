package com.example.Cines.Salas.Dominio;

import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.Salas.Dominio.ObjetosValor.MatrizAsientos;
import com.example.Cines.Salas.Dominio.ObjetosValor.ValidacionesSala;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Salas {
    private UUID id;
    private String nombre;
    private TipoSala tipoSala;
    private MatrizAsientos matrizAsientos;
    private ValidacionesSala validacionesSala;
    private Cine idCine;
    private Double promedio_valoracion;

    public Salas(UUID id, String nombre, TipoSala tipoSala, MatrizAsientos matrizAsientos, ValidacionesSala validacionesSala, Cine idCine) {
        this.id = id;
        this.nombre = nombre;
        this.tipoSala = tipoSala;
        this.matrizAsientos = matrizAsientos;
        this.validacionesSala = validacionesSala;
        this.idCine = idCine;
    }
}
