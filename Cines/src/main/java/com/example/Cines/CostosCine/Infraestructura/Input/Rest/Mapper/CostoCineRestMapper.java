package com.example.Cines.CostosCine.Infraestructura.Input.Rest.Mapper;

import com.example.Cines.CostosCine.Dominio.CostoCine;
import com.example.Cines.CostosCine.Infraestructura.Input.Rest.Models.ResponseCostoCineDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "Spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface CostoCineRestMapper {
    ResponseCostoCineDTO toReponseCineDto(CostoCine costoCine);

    List<ResponseCostoCineDTO>  toReponseCineDtoList(List<CostoCine> costoCines);
}
