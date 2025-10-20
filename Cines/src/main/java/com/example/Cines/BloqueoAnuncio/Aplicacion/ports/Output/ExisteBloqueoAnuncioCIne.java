package com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Output;

import java.time.LocalDate;
import java.util.UUID;

public interface ExisteBloqueoAnuncioCIne {

    boolean existeBloqueoAnuncioCIne(UUID cine, LocalDate fechaInicio, LocalDate fechaFin);
}
