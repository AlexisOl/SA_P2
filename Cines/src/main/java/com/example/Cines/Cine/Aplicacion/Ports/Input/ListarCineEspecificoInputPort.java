package com.example.Cines.Cine.Aplicacion.Ports.Input;

import com.example.Cines.Cine.Dominio.Cine;

import java.util.UUID;

public interface ListarCineEspecificoInputPort {
    Cine listarCineEspecifico(UUID id);
}
