package com.example.Cines.CostosCine.Aplicacion.Ports.Output;

import com.example.Cines.CostosCine.Dominio.CostoCine;

import java.util.List;
import java.util.UUID;

public interface ListarCostosCineEspecificoOutputPort {
    List<CostoCine> listarCostosCineEspecifico(UUID idCine);

}
