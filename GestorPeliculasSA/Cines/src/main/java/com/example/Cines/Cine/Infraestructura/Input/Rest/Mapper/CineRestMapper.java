package com.example.Cines.Cine.Infraestructura.Input.Rest.Mapper;

import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.Cine.Infraestructura.Input.Rest.Models.Response.ResponseCineDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "Spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface CineRestMapper {
    ResponseCineDTO toReponseCineDto(Cine cine);
}
