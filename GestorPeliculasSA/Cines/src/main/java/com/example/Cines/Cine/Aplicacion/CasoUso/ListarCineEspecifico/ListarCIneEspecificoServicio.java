package com.example.Cines.Cine.Aplicacion.CasoUso.ListarCineEspecifico;

import com.example.Cines.Cine.Aplicacion.Ports.Input.ListarCineEspecificoInputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Output.ExisteCineOutputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Output.ListarCineEspecificoOutputPort;
import com.example.Cines.Cine.Dominio.Cine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ListarCIneEspecificoServicio implements ListarCineEspecificoInputPort  {


    private final ListarCineEspecificoOutputPort listarCineEspecificoOutputPort;
    private final ExisteCineOutputPort existeCineOutputPort;


    public ListarCIneEspecificoServicio(ListarCineEspecificoOutputPort listarCineEspecificoOutputPort,
                                        ExisteCineOutputPort existeCineOutputPort) {
        this.listarCineEspecificoOutputPort = listarCineEspecificoOutputPort;
        this.existeCineOutputPort = existeCineOutputPort;
    }


    @Override
    @Transactional
    public Cine listarCineEspecifico(UUID id) {
        // ver la existencia
        if(!this.existeCineOutputPort.existeCineEspecifico(id)){
            throw new RuntimeException("El id de la Cine no existe en el sistema");
        }
        return this.listarCineEspecificoOutputPort.listarCineEspecifico(id);
    }
}
