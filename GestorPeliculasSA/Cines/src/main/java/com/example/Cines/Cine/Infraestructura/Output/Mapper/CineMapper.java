package com.example.Cines.Cine.Infraestructura.Output.Mapper;

import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.Cine.Infraestructura.Output.Entity.CineEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CineMapper {

    Cine toCine(CineEntity cineEntity);

    CineEntity toCineEntity(Cine cine);
}
