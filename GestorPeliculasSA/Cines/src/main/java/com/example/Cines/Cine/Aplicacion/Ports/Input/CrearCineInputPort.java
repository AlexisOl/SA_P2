package com.example.Cines.Cine.Aplicacion.Ports.Input;

import com.example.Cines.Cine.Aplicacion.CasoUso.CrearCine.CrearCineDTO;
import com.example.Cines.Cine.Dominio.Cine;

public interface CrearCineInputPort {

    Cine createCine(CrearCineDTO  crearCineDTO);
}
