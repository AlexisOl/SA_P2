package com.example.Cines.BloqueoAnuncio.Aplicacion.CasosUso.CrearBloqueoAnuncios;

import com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Input.CrearBloqueoAnunciosCineInputPort;
import com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Output.CrearBloqueoAnunciosCineOutputPort;
import com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Output.ExisteBloqueoAnuncioCIne;
import com.example.Cines.BloqueoAnuncio.Dominio.BloqueoAnuncio;
import com.example.Cines.Cine.Aplicacion.Ports.Input.ExisteCineInputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Output.ExisteCineOutputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Output.ListarCineEspecificoOutputPort;
import com.example.Cines.Cine.Dominio.Cine;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class CrearBloqueoAnuncioService  implements CrearBloqueoAnunciosCineInputPort {


    private CrearBloqueoAnunciosCineOutputPort crearBloqueoAnunciosCineOutputPort;
    private ExisteBloqueoAnuncioCIne existeBloqueoAnuncioCIne;
    private ExisteCineOutputPort existeCineOutputPort;
    private ListarCineEspecificoOutputPort listarCineEspecificoOutputPort;

    public CrearBloqueoAnuncioService(CrearBloqueoAnunciosCineOutputPort crearBloqueoAnunciosCineOutputPort,
                                      ExisteBloqueoAnuncioCIne existeBloqueoAnuncioCIne,
                                      ExisteCineOutputPort existeCineOutputPort,
                                      ListarCineEspecificoOutputPort listarCineEspecificoOutputPort) {
        this.crearBloqueoAnunciosCineOutputPort=crearBloqueoAnunciosCineOutputPort;
        this.existeBloqueoAnuncioCIne=existeBloqueoAnuncioCIne;
        this.existeCineOutputPort=existeCineOutputPort;
        this.listarCineEspecificoOutputPort=listarCineEspecificoOutputPort;
    }


    @Override
    public BloqueoAnuncio CrearBloqueoAnuncio(CrearBloqueoAnuncioDTO crearBloqueoAnuncioDTO) {

        LocalDate fechaFin = crearBloqueoAnuncioDTO.getFecha().plusDays(crearBloqueoAnuncioDTO.getCantidad_dias());
        System.out.println(crearBloqueoAnuncioDTO.getCine());
        //buscar si existe cine
        if (!this.existeCineOutputPort.existeCineEspecifico(crearBloqueoAnuncioDTO.getCine())){
            throw new RuntimeException("este cine no existe");
        }
        Cine cine = this.listarCineEspecificoOutputPort.listarCineEspecifico(crearBloqueoAnuncioDTO.getCine());

        System.out.println(cine.getId()+ "--- "+ crearBloqueoAnuncioDTO.getCine());

        // verificar si no existe previamente un bloqueo
        if(this.existeBloqueoAnuncioCIne.existeBloqueoAnuncioCIne(crearBloqueoAnuncioDTO.getCine(),
                                                                    crearBloqueoAnuncioDTO.getFecha(),fechaFin)) {
            System.out.println("si existe un cine ya registrado cpon bloqueo");
            throw new RuntimeException("Ya existe un bloqueo previo en esta fecha para el cine");
        }

        return this.crearBloqueoAnunciosCineOutputPort.CrearBloqueoAnuncio(
                new BloqueoAnuncio(
                        UUID.randomUUID(),
                        crearBloqueoAnuncioDTO.getFecha(),
                        fechaFin,
                        crearBloqueoAnuncioDTO.getCantidad_dias(),
                        cine
                )
        );
    }
}
