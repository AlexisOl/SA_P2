package com.example.Cines.Salas.Aplicacion.CasoUso.ListarSalasPorCine;

import com.example.Cines.Cine.Aplicacion.Ports.Output.ExisteCineOutputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Output.ListarCineEspecificoOutputPort;
import com.example.Cines.Salas.Aplicacion.Ports.Input.ListarSalasPorCineInputPort;
import com.example.Cines.Salas.Aplicacion.Ports.Output.CrearSalaEnCineOutputPort;
import com.example.Cines.Salas.Aplicacion.Ports.Output.ListarSalasPorCineOutputPort;
import com.example.Cines.Salas.Dominio.Salas;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service

public class LiatarSalasPorCineService implements ListarSalasPorCineInputPort {
    private final ListarSalasPorCineOutputPort listarSalasPorCineOutputPort;
    private final ExisteCineOutputPort existeCineOutputPort;

    public LiatarSalasPorCineService(ListarSalasPorCineOutputPort listarSalasPorCineOutputPort,
                            ExisteCineOutputPort existeCineOutputPort) {
        this.listarSalasPorCineOutputPort = listarSalasPorCineOutputPort;
        this.existeCineOutputPort = existeCineOutputPort;
    }

    @Override
    @Transactional

    public List<Salas> ListarSalasPorCine(UUID id) {
        if(!this.existeCineOutputPort.existeCineEspecifico(id)){
            throw new RuntimeException("No existe el cine con el id");
        }
        return this.listarSalasPorCineOutputPort.ListarSalasPorCine(id);
    }
}
