package com.example.Cines.BloqueoAnuncio.Aplicacion.CasosUso.CinesConAnunciosBloqueadosFecha;

import com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Input.CinesAnuncioBloqueadoFechaInputPort;
import com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Output.CinesAnuncioBloqueadoFechaOutputPort;
import com.example.Cines.BloqueoAnuncio.Dominio.BloqueoAnuncio;
import com.example.Cines.Cine.Dominio.Cine;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CinesConAnunciosBloqueadosService implements CinesAnuncioBloqueadoFechaInputPort {

    private final CinesAnuncioBloqueadoFechaOutputPort cinesAnuncioBloqueadoFechaOutputPort;
    public CinesConAnunciosBloqueadosService(CinesAnuncioBloqueadoFechaOutputPort cinesAnuncioBloqueadoFechaOutputPort){
        this.cinesAnuncioBloqueadoFechaOutputPort=cinesAnuncioBloqueadoFechaOutputPort;
    }


    @Override
    public List<BloqueoAnuncio> listaCinesAnunciosBloqueados(LocalDate fechaInicio, LocalDate fechaFin) {
        return this.cinesAnuncioBloqueadoFechaOutputPort.listaCinesAnunciosBloqueados(fechaInicio, fechaFin);
    }
}
