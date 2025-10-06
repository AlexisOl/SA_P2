package com.example.Cines.Salas.Aplicacion.Ports.Input;

import com.example.Cines.Salas.Dominio.Salas;

import java.util.UUID;

public interface ListarSalaEspecificaInputPort {
    Salas listarSalaEspecifica(UUID id);
}
