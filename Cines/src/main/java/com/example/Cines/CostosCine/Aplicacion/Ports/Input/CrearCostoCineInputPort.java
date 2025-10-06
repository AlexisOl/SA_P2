package com.example.Cines.CostosCine.Aplicacion.Ports.Input;

import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.CostosCine.Aplicacion.CasoUso.CrearCostoCIne.CrearCostosCineDTO;
import com.example.Cines.CostosCine.Dominio.CostoCine;

public interface CrearCostoCineInputPort {

    CostoCine crearCostoCine(CrearCostosCineDTO idCine);
}

