package com.example.Cines.CostosCine.Infraestructura.Output.Mapper;

import com.example.Cines.CostosCine.Dominio.CostoCine;
import com.example.Cines.CostosCine.Infraestructura.Output.Entity.CostoCineEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface CostoCIneMapper {
    CostoCine toCostoCine(CostoCineEntity costoCineEntity);
    CostoCineEntity toCostoCineEntity(CostoCine costoCine);

}
