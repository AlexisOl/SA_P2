package com.example.Cines.Cine.Aplicacion.Ports.Input;

import com.example.Cines.Cine.Aplicacion.CasoUso.EditarCine.EditarCineDto;
import com.example.Cines.Cine.Dominio.Cine;

import java.util.UUID;

public interface EditarCineInputPort {
    Cine editarCine(UUID id, EditarCineDto cine);
}
