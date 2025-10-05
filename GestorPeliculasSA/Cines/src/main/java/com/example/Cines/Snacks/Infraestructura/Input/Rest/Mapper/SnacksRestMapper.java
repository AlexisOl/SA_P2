package com.example.Cines.Snacks.Infraestructura.Input.Rest.Mapper;

import com.example.Cines.Snacks.Dominio.Snacks;
import com.example.Cines.Snacks.Infraestructura.Input.Rest.Models.ResponseSnacksDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "Spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface SnacksRestMapper {

    ResponseSnacksDTO toResponseSnacksDTO(Snacks snacks);
    List<ResponseSnacksDTO>  toResponseSnacksDTOList(List<Snacks> snacksEntity);
}
