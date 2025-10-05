package com.example.Cines.Snacks.Aplicacion.Ports.Output;

import com.example.Cines.Snacks.Dominio.Snacks;

import java.util.UUID;

public interface ListarSnacksEspecificasOutputPort {
    Snacks ListarSnacksEspecificas(UUID id);

}
