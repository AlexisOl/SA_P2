package com.example.Cines.Salas.Aplicacion.Ports.Input;

import com.example.Cines.Salas.Aplicacion.CasoUso.CrearSala.CrearSalaDTO;
import com.example.Cines.Salas.Dominio.Salas;

public interface CrearSalaEnCineInputPort {
    Salas crearSalaEnCine(CrearSalaDTO crearSalaDTO);
}
