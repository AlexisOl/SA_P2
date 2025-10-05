package com.example.Cines.Snacks.Aplicacion.CasoUso.ListarSnackEspecifica;

import com.example.Cines.Cine.Aplicacion.Ports.Output.ExisteCineOutputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Output.ListarCineEspecificoOutputPort;
import com.example.Cines.Snacks.Aplicacion.Ports.Input.ListarSnacksEspecificasInputPort;
import com.example.Cines.Snacks.Aplicacion.Ports.Output.CrearSnackOutputPort;
import com.example.Cines.Snacks.Aplicacion.Ports.Output.ListarSnacksEspecificasOutputPort;
import com.example.Cines.Snacks.Dominio.Snacks;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ListarSnackEspecificaServicio implements ListarSnacksEspecificasInputPort {
    private final ListarSnacksEspecificasOutputPort listarSnacksEspecificasOutputPort;

    public ListarSnackEspecificaServicio(ListarSnacksEspecificasOutputPort listarSnacksEspecificasOutputPort
                                         ){
        this.listarSnacksEspecificasOutputPort=listarSnacksEspecificasOutputPort;
    }

    @Override
    public Snacks ListarSnacksEspecificas(UUID id) {

        return this.listarSnacksEspecificasOutputPort.ListarSnacksEspecificas(id);
    }
}
