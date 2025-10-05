package com.example.Cines.CostosCine.Aplicacion.Ports.Input;

import com.example.Cines.CostosCine.Dominio.CostoCine;

import java.util.List;
import java.util.UUID;

public interface ListarCostosCineEspecificoInputPort {
    List<CostoCine> listarCostosCineEspecifico(UUID idCine);
}
