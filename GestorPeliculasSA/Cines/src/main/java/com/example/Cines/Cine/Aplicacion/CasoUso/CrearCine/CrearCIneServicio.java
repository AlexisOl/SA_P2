package com.example.Cines.Cine.Aplicacion.CasoUso.CrearCine;

import com.example.Cines.Cine.Aplicacion.Ports.Input.CrearCineInputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Output.CrearCineOutputPort;
import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.CostosCine.Aplicacion.Ports.Output.CrearCostoCIneOutputPort;
import com.example.Cines.CostosCine.Dominio.CostoCine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class CrearCIneServicio implements CrearCineInputPort {
    private final CrearCineOutputPort crearCineOutputPort;
    private final CrearCostoCIneOutputPort crearCostoCIneOutputPort;

    public CrearCIneServicio(CrearCineOutputPort crearCineOutputPort,
                             CrearCostoCIneOutputPort crearCostoCIneOutputPort) {
        this.crearCineOutputPort = crearCineOutputPort;
        this.crearCostoCIneOutputPort  = crearCostoCIneOutputPort;
    }



    @Override
    @Transactional
    public Cine createCine(CrearCineDTO crearCineDTO) {

        // crea el cine

        Cine cineNuevo = this.crearCineOutputPort.createCine(
                new Cine(
                        UUID.randomUUID(),
                        crearCineDTO.getNombre(),
                        crearCineDTO.getUbicacion(),
                        crearCineDTO.getTelefono(),
                        LocalDate.now()
                )
        );


        // creacion de costo
        CostoCine costoNuevo = this.crearCostoCIneOutputPort.crearCostoCine(
                new CostoCine(
                        UUID.randomUUID(),
                        LocalDate.now(),
                        crearCineDTO.getCosto(),
                        cineNuevo
                )
        );


        return cineNuevo;

    }





}
