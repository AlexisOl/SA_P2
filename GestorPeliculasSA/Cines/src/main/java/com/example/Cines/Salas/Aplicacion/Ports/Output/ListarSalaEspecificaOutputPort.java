package com.example.Cines.Salas.Aplicacion.Ports.Output;

import com.example.Cines.Salas.Dominio.Salas;

import java.util.UUID;

public interface ListarSalaEspecificaOutputPort {
    Salas listarSalaEspecifica(UUID id);

}
