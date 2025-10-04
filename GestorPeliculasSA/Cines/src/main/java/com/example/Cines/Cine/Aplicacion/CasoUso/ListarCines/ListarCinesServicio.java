package com.example.Cines.Cine.Aplicacion.CasoUso.ListarCines;

import com.example.Cines.Cine.Aplicacion.Ports.Input.ListarCinesInputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Output.ListarCinesOutputPort;
import com.example.Cines.Cine.Dominio.Cine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListarCinesServicio implements ListarCinesInputPort {

    private ListarCinesOutputPort listarCinesOutputPort;

    public ListarCinesServicio(ListarCinesOutputPort listarCinesOutputPort) {
        this.listarCinesOutputPort = listarCinesOutputPort;
    }


    @Override
    @Transactional
    public List<Cine> listarCines() {
        return this.listarCinesOutputPort.listarCines();
    }
}
