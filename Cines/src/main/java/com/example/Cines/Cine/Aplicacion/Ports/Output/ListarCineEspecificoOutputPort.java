package com.example.Cines.Cine.Aplicacion.Ports.Output;

import com.example.Cines.Cine.Dominio.Cine;

import java.util.UUID;

public interface ListarCineEspecificoOutputPort {
    Cine listarCineEspecifico(UUID id);
}
