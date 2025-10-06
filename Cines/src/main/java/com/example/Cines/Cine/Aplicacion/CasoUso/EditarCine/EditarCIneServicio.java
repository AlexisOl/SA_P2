package com.example.Cines.Cine.Aplicacion.CasoUso.EditarCine;

import com.example.Cines.Cine.Aplicacion.Ports.Input.EditarCineInputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Output.EditarCIneOutputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Output.ExisteCineOutputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Output.ListarCineEspecificoOutputPort;
import com.example.Cines.Cine.Dominio.Cine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class EditarCIneServicio implements EditarCineInputPort {

    private final EditarCIneOutputPort editarCIneOutputPort;
    private final ExisteCineOutputPort existeCineOutputPort;
    private final ListarCineEspecificoOutputPort listarCineEspecificoOutputPort;

    public EditarCIneServicio(EditarCIneOutputPort editarCIneOutputPort,
                              ExisteCineOutputPort existeCineOutputPort,
                              ListarCineEspecificoOutputPort listarCineEspecificoOutputPort) {
        this.editarCIneOutputPort = editarCIneOutputPort;
        this.listarCineEspecificoOutputPort =  listarCineEspecificoOutputPort;
        this.existeCineOutputPort = existeCineOutputPort;
    }


    @Override
    @Transactional
    public Cine editarCine(UUID id, EditarCineDto cine) {
        if(!this.existeCineOutputPort.existeCineEspecifico(id)) {
            throw new RuntimeException("No existe el cine con el id " + id);
        }
        Cine cineEspecifico = this.listarCineEspecificoOutputPort.listarCineEspecifico(id);

        // cambio de datos
        cineEspecifico.setNombre(cine.getNombre());
        cineEspecifico.setUbicacion(cine.getUbicacion());
        cineEspecifico.setTelefono(cine.getTelefono());

        return this.editarCIneOutputPort.editarCIne(cineEspecifico);
    }
}
