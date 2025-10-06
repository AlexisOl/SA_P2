package com.example.Cines.Salas.Aplicacion.Ports.Input;

import com.example.Cines.Salas.Dominio.Salas;

import java.util.List;
import java.util.UUID;

public interface ListarSalasPorCineInputPort {
    List<Salas> ListarSalasPorCine(UUID id);
}
