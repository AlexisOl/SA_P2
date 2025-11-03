package com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Output;

import java.time.LocalDate;
import java.util.UUID;

public interface CalculoDiasSolapados {
    Object diasSolapados(UUID idCine, LocalDate FECHA_INICIO, LocalDate FECHA_FINAL);
}
