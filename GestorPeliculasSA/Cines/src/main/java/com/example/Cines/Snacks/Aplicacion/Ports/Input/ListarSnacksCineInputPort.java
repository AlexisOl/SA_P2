package com.example.Cines.Snacks.Aplicacion.Ports.Input;

import com.example.Cines.Snacks.Aplicacion.CasoUso.CrearSnacks.CrearSnacksDTO;
import com.example.Cines.Snacks.Dominio.Snacks;

import java.util.List;
import java.util.UUID;

public interface ListarSnacksCineInputPort {
    List<Snacks> ListarSnack(UUID id);
}
