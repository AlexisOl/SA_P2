package com.example.Cines.Cine.Dominio;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class Cine {
    private UUID id;
    private String nombre;
    private String ubicacion;
    private String telefono;
    private LocalDate fechaCreacion;
    private Double costoOcultacion;
    private double cartera;


    public Cine(UUID id, String nombre, String ubicacion, String telefono, LocalDate fechaCreacion,Double costoOcultacion,
                double cartera) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.fechaCreacion = fechaCreacion;
        this.costoOcultacion = costoOcultacion;
        this.cartera = cartera;
    }
}
