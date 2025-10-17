package com.example.Cines.Cine.Infraestructura.Output;


import com.example.Cines.Cine.Aplicacion.Ports.Output.*;
import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.Cine.Infraestructura.Output.Mapper.CineMapper;
import com.example.Cines.Cine.Infraestructura.Output.Repository.CineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CineAdaptadorPersistencia implements CrearCineOutputPort, ListarCinesOutputPort, ListarCineEspecificoOutputPort, EditarCIneOutputPort,
        ExisteCineOutputPort,
        CambioMonetarioOutputPort{

    private final CineRepository cineRepository;
    private final CineMapper cineMapper;


    @Override
    @Transactional
    public Cine createCine(Cine cine) {
        return this.cineMapper.toCine(
                this.cineRepository.save(
                        this.cineMapper.toCineEntity(cine)
                )
        );
    }


    @Override
    @Transactional
    public Cine editarCIne(Cine cine) {
        return this.cineMapper.toCine(
                this.cineRepository.save(
                        this.cineMapper.toCineEntity(cine)
                )
        );
    }

    @Override
    @Transactional
    public Cine listarCineEspecifico(UUID id) {
        return this.cineMapper.toCine(
                this.cineRepository.findById(id).get()
        );
    }

    @Override
    @Transactional

    public List<Cine> listarCines() {
        return this.cineMapper.toCineList(
                this.cineRepository.findAll()
        ) ;
    }

    @Override
    @Transactional

    public Boolean existeCineEspecifico(UUID id) {
        return this.cineRepository.existsById(id);
    }

    @Override
    public Boolean cambioMonetario(UUID id, Double cantidad, Boolean estadoCambio) {
        return this.cineRepository.ingresoSaldo(
                id, estadoCambio, cantidad
        );
    }
}
