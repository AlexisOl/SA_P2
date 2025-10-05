package com.example.Cines.Salas.Aplicacion.CasoUso.CrearSala;

import com.example.Cines.Cine.Aplicacion.Ports.Output.ExisteCineOutputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Output.ListarCineEspecificoOutputPort;
import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.Salas.Aplicacion.Ports.Input.CrearSalaEnCineInputPort;
import com.example.Cines.Salas.Aplicacion.Ports.Output.CrearSalaEnCineOutputPort;
import com.example.Cines.Salas.Dominio.ObjetosValor.MatrizAsientos;
import com.example.Cines.Salas.Dominio.ObjetosValor.ValidacionesSala;
import com.example.Cines.Salas.Dominio.Salas;
import com.example.Cines.Salas.Dominio.TipoSala;
import com.example.Cines.Snacks.Aplicacion.Ports.Output.CrearSnackOutputPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CrearSalaService implements CrearSalaEnCineInputPort {

    private final CrearSalaEnCineOutputPort crearSalaEnCineOutputPort;
    private final ExisteCineOutputPort existeCineOutputPort;
    private final ListarCineEspecificoOutputPort listarCineEspecificoOutputPort;

    public CrearSalaService(CrearSalaEnCineOutputPort crearSalaEnCineOutputPort,
                            ExisteCineOutputPort existeCineOutputPort,
                            ListarCineEspecificoOutputPort listarCineEspecificoOutputPort) {
        this.crearSalaEnCineOutputPort = crearSalaEnCineOutputPort;
        this.existeCineOutputPort = existeCineOutputPort;
        this.listarCineEspecificoOutputPort = listarCineEspecificoOutputPort;
    }
    @Override
    @Transactional
    public Salas crearSalaEnCine(CrearSalaDTO crearSalaDTO) {
        //exitencia
        if(!this.existeCineOutputPort.existeCineEspecifico(crearSalaDTO.getIdCine())){
            throw new RuntimeException("No existe el cine con el id");
        }
        Cine cineEspecifico = this.listarCineEspecificoOutputPort.listarCineEspecifico(crearSalaDTO.getIdCine());


        return this.crearSalaEnCineOutputPort.crearSalaEnCine(
                new Salas(
                        UUID.randomUUID(),
                        crearSalaDTO.getNombre(),
                        TipoSala.valueOf(crearSalaDTO.getTipoSala()),
                        new MatrizAsientos(
                                crearSalaDTO.getMatrizAsientos().getFilas(),
                                crearSalaDTO.getMatrizAsientos().getColumnas()
                        ),
                        new ValidacionesSala(
                                crearSalaDTO.getValidacionesSala().isValidar_comnentarios(),
                                crearSalaDTO.getValidacionesSala().isValidar_calificaciones(),
                                crearSalaDTO.getValidacionesSala().isVisible()
                        ),
                        cineEspecifico
                )
        );
    }
}
