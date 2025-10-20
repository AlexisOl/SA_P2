package com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Output;


import com.example.Cines.BloqueoAnuncio.Dominio.BloqueoAnuncio;

import java.util.List;
import java.util.UUID;

public interface ListadoBloqueosCineOutputPort {
    List<BloqueoAnuncio> listadoBloqueosCine(UUID idCine);

}
