package com.example.Cines.Cine.Aplicacion.Ports.Output;

import com.example.Cines.Cine.Dominio.Cine;

import java.util.UUID;

public interface CambioMonetarioOutputPort {
    // aca el estado es para ver si quita o agrega - ver bien
    Boolean cambioMonetario(UUID id, Double cantidad, Boolean estadoCambio);
}
