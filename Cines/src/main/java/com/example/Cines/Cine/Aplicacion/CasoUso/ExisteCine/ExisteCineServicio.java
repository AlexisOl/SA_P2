package com.example.Cines.Cine.Aplicacion.CasoUso.ExisteCine;

import com.example.Cines.Cine.Aplicacion.Ports.Input.ExisteCineInputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Output.ExisteCineOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ExisteCineServicio implements ExisteCineInputPort {
    private final ExisteCineOutputPort existeCineOutputPort;

    public ExisteCineServicio(ExisteCineOutputPort existeCineOutputPort) {
        this.existeCineOutputPort = existeCineOutputPort;
    }

    @Override
    public Boolean existeCineEspecifico(UUID id) {
        return this.existeCineOutputPort.existeCineEspecifico(id);
    }
}
