package com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Output;

import java.time.LocalDate;
import java.util.UUID;

public interface DeterminarDiasSolapadosBloqueoOutputport {

    Object diasSolapadosBloqueo(UUID idCine, LocalDate FECHA_INICIO, LocalDate FECHA_FINAL, LocalDate FECHA_INICIO_bloqueo, LocalDate FECHA_FINAL_bloqueo);

}
