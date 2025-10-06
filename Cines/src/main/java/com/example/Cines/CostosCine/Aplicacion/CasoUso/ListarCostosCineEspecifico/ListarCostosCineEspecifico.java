package com.example.Cines.CostosCine.Aplicacion.CasoUso.ListarCostosCineEspecifico;

import com.example.Cines.Cine.Aplicacion.Ports.Output.ExisteCineOutputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Output.ListarCineEspecificoOutputPort;
import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.CostosCine.Aplicacion.Ports.Input.ListarCostosCineEspecificoInputPort;
import com.example.Cines.CostosCine.Aplicacion.Ports.Output.CrearCostoCIneOutputPort;
import com.example.Cines.CostosCine.Aplicacion.Ports.Output.ListarCostosCineEspecificoOutputPort;
import com.example.Cines.CostosCine.Dominio.CostoCine;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListarCostosCineEspecifico implements ListarCostosCineEspecificoInputPort {


    private final ListarCostosCineEspecificoOutputPort listarCostosCineEspecificoOutputPort;
    private final ExisteCineOutputPort existeCineOutputPort;
    private final ListarCineEspecificoOutputPort listarCineEspecificoOutputPort;

    public ListarCostosCineEspecifico(ListarCostosCineEspecificoOutputPort listarCostosCineEspecificoOutputPort,
                           ExisteCineOutputPort existeCineOutputPort,
                           ListarCineEspecificoOutputPort listarCineEspecificoOutputPort){
        this.listarCostosCineEspecificoOutputPort=listarCostosCineEspecificoOutputPort;
        this.existeCineOutputPort=existeCineOutputPort;
        this.listarCineEspecificoOutputPort=listarCineEspecificoOutputPort;
    }

    @Override
    public List<CostoCine> listarCostosCineEspecifico(UUID idCine) {
        //exitencia
        if(!this.existeCineOutputPort.existeCineEspecifico(idCine)){
            throw new RuntimeException("No existe el cine con el id");
        }
        return this.listarCostosCineEspecificoOutputPort.listarCostosCineEspecifico(idCine) ;
    }
}
