package com.example.Cines.Salas.Aplicacion.CasoUso.CambiarVisibildadComentarios;

import com.example.Cines.Salas.Aplicacion.Ports.Input.CambiarVisibildadComentariosInputPort;
import com.example.Cines.Salas.Aplicacion.Ports.Output.CambiarVisibildadComentariosOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CambiarVisibilidadComentariosServices implements CambiarVisibildadComentariosInputPort {
    private final CambiarVisibildadComentariosOutputPort cambiarVisibildadComentariosOutputPort;

    public CambiarVisibilidadComentariosServices(CambiarVisibildadComentariosOutputPort cambiarVisibildadComentariosOutputPort){
        this.cambiarVisibildadComentariosOutputPort=cambiarVisibildadComentariosOutputPort;
    }
    @Override
    public void cambiarVisbilidadComentarios(UUID id) {
        this.cambiarVisibildadComentariosOutputPort.cambiarVisbilidadComentarios(id);

    }
}
