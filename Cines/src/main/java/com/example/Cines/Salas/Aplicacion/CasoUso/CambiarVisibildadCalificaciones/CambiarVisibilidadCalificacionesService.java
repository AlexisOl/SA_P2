package com.example.Cines.Salas.Aplicacion.CasoUso.CambiarVisibildadCalificaciones;

import com.example.Cines.Salas.Aplicacion.Ports.Input.CambiarVisibildadCalificacionesInputPort;
import com.example.Cines.Salas.Aplicacion.Ports.Output.CambiarVisibildadCalificacionesOutputPort;
import com.example.Cines.Salas.Aplicacion.Ports.Output.CambiarVisibildadComentariosOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CambiarVisibilidadCalificacionesService implements CambiarVisibildadCalificacionesInputPort {
    private final CambiarVisibildadCalificacionesOutputPort cambiarVisibildadCalificacionesOutputPort;

    public CambiarVisibilidadCalificacionesService(CambiarVisibildadCalificacionesOutputPort cambiarVisibildadCalificacionesOutputPort){
        this.cambiarVisibildadCalificacionesOutputPort=cambiarVisibildadCalificacionesOutputPort;
    }
    @Override
    public void cambiarVisbilidadCalificaciones(UUID id) {
        this.cambiarVisibildadCalificacionesOutputPort.cambiarVisbilidadCalificaciones(id);
    }
}
