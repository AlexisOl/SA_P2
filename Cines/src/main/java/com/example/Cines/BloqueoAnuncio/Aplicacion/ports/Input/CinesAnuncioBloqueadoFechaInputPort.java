package com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Input;

import com.example.Cines.BloqueoAnuncio.Dominio.BloqueoAnuncio;
import com.example.Cines.Cine.Dominio.Cine;

import java.time.LocalDate;
import java.util.List;

public interface CinesAnuncioBloqueadoFechaInputPort {

    List<BloqueoAnuncio> listaCinesAnunciosBloqueados(LocalDate fechaInicio, LocalDate fechaFin);
}
