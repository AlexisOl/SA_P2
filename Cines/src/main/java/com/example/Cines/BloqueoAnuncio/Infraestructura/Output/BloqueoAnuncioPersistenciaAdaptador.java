package com.example.Cines.BloqueoAnuncio.Infraestructura.Output;

import com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Output.*;
import com.example.Cines.BloqueoAnuncio.Dominio.BloqueoAnuncio;
import com.example.Cines.BloqueoAnuncio.Infraestructura.Output.Mapper.BloqueoAnuncioMapper;
import com.example.Cines.BloqueoAnuncio.Infraestructura.Output.Repository.BloqueoAnuncioRepository;
import com.example.Cines.Cine.Dominio.Cine;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class BloqueoAnuncioPersistenciaAdaptador implements CrearBloqueoAnunciosCineOutputPort, ExisteBloqueoAnuncioCIne,
        ListadoBloqueosCineOutputPort, CineConBloqueoActualmenteOutputPort, CinesAnuncioBloqueadoFechaOutputPort, CalculoDiasSolapados,
        ElimiinarBLoqueoCineEspecificoOutputPort, DeterminarDiasSolapadosBloqueoOutputport{
    private BloqueoAnuncioMapper bloqueoAnuncioMapper;
    private BloqueoAnuncioRepository bloqueoAnuncioRepository;


    @Override
    @Transactional
    public BloqueoAnuncio CrearBloqueoAnuncio(BloqueoAnuncio bloqueoAnuncio) {
        return this.bloqueoAnuncioMapper.toBloqueoAnuncio(
                this.bloqueoAnuncioRepository.save(
                        this.bloqueoAnuncioMapper.toBloqueoAnuncioEntity(bloqueoAnuncio)
                )
        );
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeBloqueoAnuncioCIne(UUID cine, LocalDate fechaInicio, LocalDate fechaFin) {
        return this.bloqueoAnuncioRepository.existeBloqueoPrevioENCine(cine, fechaInicio, fechaFin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BloqueoAnuncio> listadoBloqueosCine(UUID idCine) {
        return this.bloqueoAnuncioMapper.toBloqueoAnuncioList(
                this.bloqueoAnuncioRepository.findAllByIdCine_Id(idCine)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean cineConBloqueo(UUID id) {
        return this.bloqueoAnuncioRepository.existeBloqueCineActualemente(id, LocalDate.now());
    }

    @Override
    @Transactional(readOnly = true)

    public List<BloqueoAnuncio> listaCinesAnunciosBloqueados(LocalDate fechaInicio, LocalDate fechaFin) {
        return
                this.bloqueoAnuncioMapper.toBloqueoAnuncioList(
                        this.bloqueoAnuncioRepository.cineBloqueoEnFechaDeterminada(
                                fechaInicio,
                                fechaFin
                        )
                );
    }

    @Override
    public Object diasSolapados(UUID idCine, LocalDate FECHA_INICIO, LocalDate FECHA_FINAL) {
        return this.bloqueoAnuncioRepository.determinaDiasSolapados(idCine, FECHA_INICIO, FECHA_FINAL);
    }

    @Override
    public void elimiinarBLoqueoCineEspecificoOutputPort(UUID id) {
        this.bloqueoAnuncioRepository.deleteById(id);

    }

    @Override
    public Object diasSolapadosBloqueo(UUID idCine, LocalDate FECHA_INICIO, LocalDate FECHA_FINAL,  LocalDate FECHA_INICIO_bloqueo, LocalDate FECHA_FINAL_bloqueo) {
        return this.bloqueoAnuncioRepository.determinaDiasSolapadosBloqueoAnuncio(idCine, FECHA_INICIO, FECHA_FINAL, FECHA_INICIO_bloqueo, FECHA_FINAL_bloqueo);
    }
}
