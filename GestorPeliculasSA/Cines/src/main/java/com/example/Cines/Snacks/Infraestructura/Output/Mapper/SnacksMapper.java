package com.example.Cines.Snacks.Infraestructura.Output.Mapper;

import com.example.Cines.Snacks.Dominio.Snacks;
import com.example.Cines.Snacks.Infraestructura.Output.Entity.SnacksEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface SnacksMapper {
    Snacks toSnacks(SnacksEntity snacksEntity);
    SnacksEntity toSnacksEntity(Snacks snacks);
    List<Snacks> toSnacksList(List<SnacksEntity> snacksEntity);
    List<SnacksEntity> toSnacksEntityList(List<Snacks> snacksEntity);
}
