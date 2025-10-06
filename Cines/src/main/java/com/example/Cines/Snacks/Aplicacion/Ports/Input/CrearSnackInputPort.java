package com.example.Cines.Snacks.Aplicacion.Ports.Input;

import com.example.Cines.Snacks.Aplicacion.CasoUso.CrearSnacks.CrearSnacksDTO;
import com.example.Cines.Snacks.Dominio.Snacks;

public interface CrearSnackInputPort {
     Snacks CrearSnack(CrearSnacksDTO dto);
}
