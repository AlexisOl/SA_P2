package com.example.Cines.Cine.Aplicacion.Ports.Output;

import com.example.Cines.Cine.Dominio.Cine;

import java.util.List;

public interface ListarCinesOutputPort {
    List<Cine> listarCines();
}
