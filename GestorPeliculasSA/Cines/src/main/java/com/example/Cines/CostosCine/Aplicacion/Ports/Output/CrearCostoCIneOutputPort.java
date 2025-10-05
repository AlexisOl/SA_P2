package com.example.Cines.CostosCine.Aplicacion.Ports.Output;

import com.example.Cines.CostosCine.Dominio.CostoCine;
import com.example.Cines.CostosCine.Infraestructura.Output.Entity.CostoCineEntity;

public interface CrearCostoCIneOutputPort {

    CostoCine crearCostoCine(CostoCine cine);
}
