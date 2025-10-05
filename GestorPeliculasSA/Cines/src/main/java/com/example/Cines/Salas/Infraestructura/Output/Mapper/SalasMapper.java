package com.example.Cines.Salas.Infraestructura.Output.Mapper;

import com.example.Cines.Salas.Dominio.Salas;
import com.example.Cines.Salas.Infraestructura.Output.Entity.SalasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface SalasMapper {
    Salas toSalas(SalasEntity  salasEntity);
    @Mapping(source = "matrizAsientos.filas", target = "filas")
    @Mapping(source = "matrizAsientos.columnas", target = "columnas")
    @Mapping(source = "validacionesSala.validar_calificaciones", target = "validarCalificaciones")
    @Mapping(source = "validacionesSala.validar_comnentarios", target = "validarComnentarios")
    @Mapping(source = "validacionesSala.visible", target ="visible")
    SalasEntity  toSalasEntity(Salas salas);

    List<Salas> toSalasList(List<SalasEntity> salasEntity);
    List<SalasEntity> toSalasEntityList(List<Salas> salas);
}
