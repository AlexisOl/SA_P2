package com.example.Cines.BloqueoAnuncio.Aplicacion.CasosUso.CIneConAnunciosBloqueadosActualmente;

import com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Input.CineConBloqueoActualmenteInputPort;
import com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Output.CineConBloqueoActualmenteOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CineConBloqueoActualmente implements CineConBloqueoActualmenteInputPort {

    private final CineConBloqueoActualmenteOutputPort cineConBloqueoActualmenteOutputPort;

    public CineConBloqueoActualmente(CineConBloqueoActualmenteOutputPort cineConBloqueoActualmenteOutputPort){
        this.cineConBloqueoActualmenteOutputPort=cineConBloqueoActualmenteOutputPort;
    }


    @Override
    public Boolean cineConBloqueo(UUID id) {
        return this.cineConBloqueoActualmenteOutputPort.cineConBloqueo(id);
    }
}
