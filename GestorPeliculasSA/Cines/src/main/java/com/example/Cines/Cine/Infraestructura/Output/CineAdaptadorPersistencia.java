package com.example.Cines.Cine.Infraestructura.Output;


import com.example.Cines.Cine.Aplicacion.Ports.Output.CrearCineOutputPort;
import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.Cine.Infraestructura.Output.Mapper.CineMapper;
import com.example.Cines.Cine.Infraestructura.Output.Repository.CineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class CineAdaptadorPersistencia implements CrearCineOutputPort {

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
}
