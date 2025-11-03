package com.example.Cines.Cine.Aplicacion.Ports.Input;

import com.example.Cines.Cine.Dominio.Cine;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ListarCinesQueNoEstanBloqueadosInputPort {
    List<Cine>  listaCinesAnunciosNoBloqueados(LocalDate fecha, LocalDate fechaFin);
}
