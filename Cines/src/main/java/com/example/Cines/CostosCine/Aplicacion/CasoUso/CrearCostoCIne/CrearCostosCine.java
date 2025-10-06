package com.example.Cines.CostosCine.Aplicacion.CasoUso.CrearCostoCIne;

import com.example.Cines.Cine.Aplicacion.Ports.Output.ExisteCineOutputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Output.ListarCineEspecificoOutputPort;
import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.CostosCine.Aplicacion.Ports.Input.CrearCostoCineInputPort;
import com.example.Cines.CostosCine.Aplicacion.Ports.Output.CrearCostoCIneOutputPort;
import com.example.Cines.CostosCine.Aplicacion.Ports.Output.ListarCostosCineEspecificoOutputPort;
import com.example.Cines.CostosCine.Dominio.CostoCine;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class CrearCostosCine implements CrearCostoCineInputPort {

    private final CrearCostoCIneOutputPort crearCostoCIneOutputPort;
    private final ExisteCineOutputPort existeCineOutputPort;
    private final ListarCineEspecificoOutputPort listarCineEspecificoOutputPort;

    public CrearCostosCine(CrearCostoCIneOutputPort crearCostoCIneOutputPort,
                           ExisteCineOutputPort existeCineOutputPort,
                           ListarCineEspecificoOutputPort listarCineEspecificoOutputPort){
        this.crearCostoCIneOutputPort=crearCostoCIneOutputPort;
        this.existeCineOutputPort=existeCineOutputPort;
        this.listarCineEspecificoOutputPort=listarCineEspecificoOutputPort;
    }

    @Override
    public CostoCine crearCostoCine(CrearCostosCineDTO idCine) {
        //exitencia
        if(!this.existeCineOutputPort.existeCineEspecifico(idCine.getIdCine())){
            throw new RuntimeException("No existe el cine con el id");
        }
        Cine cineEspecifico = this.listarCineEspecificoOutputPort.listarCineEspecifico(idCine.getIdCine());

        // puede ser simpre con hoy o despues , pero ver bien
        return this.crearCostoCIneOutputPort.crearCostoCine(
            new CostoCine(
                    UUID.randomUUID(),
                    LocalDate.now(),
                    idCine.getCosto(),
                    cineEspecifico
            )
        );
    }
}
