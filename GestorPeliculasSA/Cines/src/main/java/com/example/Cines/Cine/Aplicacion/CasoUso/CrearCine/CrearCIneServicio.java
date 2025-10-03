package com.example.Cines.Cine.Aplicacion.CasoUso.CrearCine;

import com.example.Cines.Cine.Aplicacion.Ports.Input.CrearCineInputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Output.CrearCineOutputPort;
import com.example.Cines.Cine.Dominio.Cine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class CrearCIneServicio implements CrearCineInputPort {
    private final CrearCineOutputPort crearCineOutputPort;


    public CrearCIneServicio(CrearCineOutputPort crearCineOutputPort) {
        this.crearCineOutputPort = crearCineOutputPort;
    }



    @Override
    @Transactional
    public Cine createCine(CrearCineDTO crearCineDTO) {




        return  this.crearCineOutputPort.createCine(
                new Cine(
                        UUID.randomUUID(),
                        crearCineDTO.getNombre(),
                        crearCineDTO.getUbicacion(),
                        crearCineDTO.getTelefono(),
                        LocalDate.now()
                )
        );
    }





}
