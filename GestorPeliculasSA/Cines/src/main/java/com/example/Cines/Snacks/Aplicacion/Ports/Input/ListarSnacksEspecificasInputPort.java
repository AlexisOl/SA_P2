package com.example.Cines.Snacks.Aplicacion.Ports.Input;

import com.example.Cines.Snacks.Dominio.Snacks;

import java.util.UUID;

public interface ListarSnacksEspecificasInputPort {
    Snacks ListarSnacksEspecificas(UUID id);

}
