package com.example.Cines.Salas.Aplicacion.CasoUso.CambiarVisibildad;

import com.example.Cines.Salas.Aplicacion.Ports.Input.CambiarVisibildadInputPort;
import com.example.Cines.Salas.Aplicacion.Ports.Output.CambiarVisibildadOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CambiarVisibilidadService implements CambiarVisibildadInputPort {

    private final CambiarVisibildadOutputPort cambiarVisibildadOutputPort;

    public CambiarVisibilidadService (
            CambiarVisibildadOutputPort cambiarVisibildadOutputPort
    ) {
        this.cambiarVisibildadOutputPort=cambiarVisibildadOutputPort;
    }
    @Override
    public void cambiarVisbilidad(UUID id) {
        this.cambiarVisibildadOutputPort.cambiarVisbilidad(id);
    }
}
