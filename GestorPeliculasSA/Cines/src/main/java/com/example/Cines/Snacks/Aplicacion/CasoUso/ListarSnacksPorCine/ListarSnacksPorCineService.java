package com.example.Cines.Snacks.Aplicacion.CasoUso.ListarSnacksPorCine;


import com.example.Cines.Cine.Aplicacion.Ports.Output.ExisteCineOutputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Output.ListarCineEspecificoOutputPort;
import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.Snacks.Aplicacion.Ports.Input.ListarSnacksCineInputPort;
import com.example.Cines.Snacks.Aplicacion.Ports.Output.CrearSnackOutputPort;
import com.example.Cines.Snacks.Aplicacion.Ports.Output.ListarSnacksCineOutputPort;
import com.example.Cines.Snacks.Dominio.Snacks;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListarSnacksPorCineService implements ListarSnacksCineInputPort {
    private final ListarSnacksCineOutputPort listarSnacksCineOutputPort;
    private final ExisteCineOutputPort existeCineOutputPort;

    public ListarSnacksPorCineService(ListarSnacksCineOutputPort listarSnacksCineOutputPort,
                              ExisteCineOutputPort existeCineOutputPort
                             ){
        this.listarSnacksCineOutputPort=listarSnacksCineOutputPort;
        this.existeCineOutputPort=existeCineOutputPort;
    }


    @Override
    public List<Snacks> ListarSnack(UUID id) {
        if(!this.existeCineOutputPort.existeCineEspecifico(id)){
            throw new RuntimeException("No existe el cine con el id");
        }


        return this.listarSnacksCineOutputPort.ListarSnack(id);
    }
}
