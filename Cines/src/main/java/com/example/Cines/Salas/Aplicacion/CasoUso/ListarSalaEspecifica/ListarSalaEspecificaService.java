package com.example.Cines.Salas.Aplicacion.CasoUso.ListarSalaEspecifica;

import com.example.Cines.Cine.Aplicacion.Ports.Output.ExisteCineOutputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Output.ListarCineEspecificoOutputPort;
import com.example.Cines.Salas.Aplicacion.Ports.Input.ListarSalaEspecificaInputPort;
import com.example.Cines.Salas.Aplicacion.Ports.Output.CrearSalaEnCineOutputPort;
import com.example.Cines.Salas.Aplicacion.Ports.Output.ListarSalaEspecificaOutputPort;
import com.example.Cines.Salas.Dominio.Salas;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service

public class ListarSalaEspecificaService implements ListarSalaEspecificaInputPort {
    private final ListarSalaEspecificaOutputPort listarSalaEspecificaOutputPort;

    public ListarSalaEspecificaService(ListarSalaEspecificaOutputPort listarSalaEspecificaOutputPort
                          ) {
        this.listarSalaEspecificaOutputPort = listarSalaEspecificaOutputPort;
    }

    @Override
    @Transactional

    public Salas listarSalaEspecifica(UUID id) {
        return this.listarSalaEspecificaOutputPort.listarSalaEspecifica(id);
    }
}
