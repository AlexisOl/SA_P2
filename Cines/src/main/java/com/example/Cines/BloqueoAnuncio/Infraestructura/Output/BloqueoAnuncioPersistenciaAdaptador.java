package com.example.Cines.BloqueoAnuncio.Infraestructura.Output;

import com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Output.CrearBloqueoAnunciosCineOutputPort;
import com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Output.ExisteBloqueoAnuncioCIne;
import com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Output.ListadoBloqueosCineOutputPort;
import com.example.Cines.BloqueoAnuncio.Dominio.BloqueoAnuncio;
import com.example.Cines.BloqueoAnuncio.Infraestructura.Output.Mapper.BloqueoAnuncioMapper;
import com.example.Cines.BloqueoAnuncio.Infraestructura.Output.Repository.BloqueoAnuncioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class BloqueoAnuncioPersistenciaAdaptador implements CrearBloqueoAnunciosCineOutputPort, ExisteBloqueoAnuncioCIne,
        ListadoBloqueosCineOutputPort {
    private BloqueoAnuncioMapper bloqueoAnuncioMapper;
    private BloqueoAnuncioRepository bloqueoAnuncioRepository;


    @Override
    public BloqueoAnuncio CrearBloqueoAnuncio(BloqueoAnuncio bloqueoAnuncio) {
        return this.bloqueoAnuncioMapper.toBloqueoAnuncio(
                this.bloqueoAnuncioRepository.save(
                        this.bloqueoAnuncioMapper.toBloqueoAnuncioEntity(bloqueoAnuncio)
                )
        );
    }

    @Override
    public boolean existeBloqueoAnuncioCIne(UUID cine, LocalDate fechaInicio, LocalDate fechaFin) {
        return this.bloqueoAnuncioRepository.existeBloqueoPrevioENCine(cine, fechaInicio, fechaFin);
    }

    @Override
    public List<BloqueoAnuncio> listadoBloqueosCine(UUID idCine) {
        return this.bloqueoAnuncioMapper.toBloqueoAnuncioList(
                this.bloqueoAnuncioRepository.findAllByIdCine_Id(idCine)
        );
    }
}
