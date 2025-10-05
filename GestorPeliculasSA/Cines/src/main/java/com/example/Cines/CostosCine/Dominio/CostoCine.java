package com.example.Cines.CostosCine.Dominio;

import com.example.Cines.Cine.Dominio.Cine;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class CostoCine {
    private UUID id;
    private LocalDate fecha;
    private Double costo;
    private Cine idCine;


    public CostoCine(UUID id, LocalDate fecha, Double costo, Cine idCine) {
        this.id = id;
        this.fecha = fecha;
        this.costo = costo;
        this.idCine = idCine;
    }
}
