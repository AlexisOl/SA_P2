package com.example.Cines.Snacks.Aplicacion.CasoUso.CrearSnacks;

import com.example.Cines.Cine.Aplicacion.Ports.Output.ExisteCineOutputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Output.ListarCineEspecificoOutputPort;
import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.CostosCine.Aplicacion.Ports.Output.CrearCostoCIneOutputPort;
import com.example.Cines.Snacks.Aplicacion.Ports.Input.CrearSnackInputPort;
import com.example.Cines.Snacks.Aplicacion.Ports.Output.CrearSnackOutputPort;
import com.example.Cines.Snacks.Dominio.Snacks;
import com.example.Cines.Snacks.Dominio.TipoSnacks;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CrearSnacksService implements CrearSnackInputPort {

    private final CrearSnackOutputPort crearSnackOutputPort;
    private final ExisteCineOutputPort existeCineOutputPort;
    private final ListarCineEspecificoOutputPort listarCineEspecificoOutputPort;

    public CrearSnacksService(CrearSnackOutputPort crearSnackOutputPort,
                           ExisteCineOutputPort existeCineOutputPort,
                           ListarCineEspecificoOutputPort listarCineEspecificoOutputPort){
        this.crearSnackOutputPort=crearSnackOutputPort;
        this.existeCineOutputPort=existeCineOutputPort;
        this.listarCineEspecificoOutputPort=listarCineEspecificoOutputPort;
    }

    @Override
    public Snacks CrearSnack(CrearSnacksDTO dto) {
        //exitencia
        if(!this.existeCineOutputPort.existeCineEspecifico(dto.getIdCine())){
            throw new RuntimeException("No existe el cine con el id");
        }
        Cine cineEspecifico = this.listarCineEspecificoOutputPort.listarCineEspecifico(dto.getIdCine());

        return this.crearSnackOutputPort.CrearSnack(
                new Snacks(
                        UUID.randomUUID(),
                        dto.getNombre(),
                        dto.getPrecio(),
                        TipoSnacks.valueOf(dto.getTipo()),
                        cineEspecifico
                )
        );
    }
}
