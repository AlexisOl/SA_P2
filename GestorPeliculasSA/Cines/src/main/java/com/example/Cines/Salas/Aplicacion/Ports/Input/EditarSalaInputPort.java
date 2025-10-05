package com.example.Cines.Salas.Aplicacion.Ports.Input;

import com.example.Cines.Salas.Aplicacion.CasoUso.EditarSala.EditarSalaDTO;
import com.example.Cines.Salas.Dominio.Salas;

import java.util.UUID;

public interface EditarSalaInputPort {
    Salas editarSala(UUID id, EditarSalaDTO salas);

}
