package com.example.Cines.Cine.Aplicacion.Ports.Output;

import com.example.Cines.Cine.Aplicacion.CasoUso.CrearCine.CrearCineDTO;
import com.example.Cines.Cine.Dominio.Cine;

public interface CrearCineOutputPort {
    Cine createCine(Cine cine);

}
