package com.example.Cines.Cine.Infraestructura.Output.Mapper;

import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.Cine.Infraestructura.Output.Entity.CineEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CineMapper {

    Cine toCine(CineEntity cineEntity);

    CineEntity toCineEntity(Cine cine);

    List<CineEntity> toCineEntityList(List<Cine> cines);

    List<Cine> toCineList(List<CineEntity> cines);
}
