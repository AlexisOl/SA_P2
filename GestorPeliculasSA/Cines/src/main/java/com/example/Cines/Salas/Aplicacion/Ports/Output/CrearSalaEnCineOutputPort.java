package com.example.Cines.Salas.Aplicacion.Ports.Output;

import com.example.Cines.Salas.Aplicacion.CasoUso.CrearSala.CrearSalaDTO;
import com.example.Cines.Salas.Dominio.Salas;

public interface CrearSalaEnCineOutputPort {
    Salas crearSalaEnCine(Salas salas);

}
