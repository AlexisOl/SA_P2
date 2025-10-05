package com.example.Cines.Snacks.Aplicacion.Ports.Output;

import com.example.Cines.Snacks.Aplicacion.CasoUso.CrearSnacks.CrearSnacksDTO;
import com.example.Cines.Snacks.Dominio.Snacks;

public interface CrearSnackOutputPort {
    Snacks CrearSnack(Snacks snacks);
}
