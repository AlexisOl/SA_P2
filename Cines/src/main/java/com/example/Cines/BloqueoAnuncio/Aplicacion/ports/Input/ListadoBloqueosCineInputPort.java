package com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Input;


import com.example.Cines.BloqueoAnuncio.Dominio.BloqueoAnuncio;

import java.util.List;
import java.util.UUID;

public interface ListadoBloqueosCineInputPort {
    List<BloqueoAnuncio> listadoBloqueosCine(UUID  idCine);
}
