package com.example.Cines.Salas.Aplicacion.Ports.Output;

import com.example.Cines.Salas.Dominio.Salas;

import java.util.List;
import java.util.UUID;

public interface ListarSalasPorCineOutputPort {
    List<Salas> ListarSalasPorCine(UUID id);

}
