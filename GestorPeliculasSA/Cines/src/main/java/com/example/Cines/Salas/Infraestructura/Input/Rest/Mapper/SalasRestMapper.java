package com.example.Cines.Salas.Infraestructura.Input.Rest.Mapper;

import com.example.Cines.Salas.Dominio.Salas;
import com.example.Cines.Salas.Infraestructura.Input.Rest.Models.ResponseSalasDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "Spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface SalasRestMapper {

    ResponseSalasDTO toResponseSalasDTO(Salas salas) ;
    List<ResponseSalasDTO> toResponseSalasDTOList(List<Salas> salas) ;
}
