package com.example.Cines.CostosCine.Infraestructura.Output;

import com.example.Cines.CostosCine.Aplicacion.Ports.Output.CrearCostoCIneOutputPort;
import com.example.Cines.CostosCine.Aplicacion.Ports.Output.ListarCostosCineEspecificoOutputPort;
import com.example.Cines.CostosCine.Dominio.CostoCine;
import com.example.Cines.CostosCine.Infraestructura.Output.Mapper.CostoCIneMapper;
import com.example.Cines.CostosCine.Infraestructura.Output.Repository.CostoCineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CostoCInePersistenciaAdapatador implements CrearCostoCIneOutputPort, ListarCostosCineEspecificoOutputPort {
    private final CostoCineRepository costoCineRepository;
    private final CostoCIneMapper costoCIneMapper;

    @Override
    public CostoCine crearCostoCine(CostoCine  cine) {
        return this.costoCIneMapper.toCostoCine(
                this.costoCineRepository.save(
                        this.costoCIneMapper.toCostoCineEntity(
                                cine
                        )
                )
        );
    }

    @Override
    public List<CostoCine> listarCostosCineEspecifico(UUID idCine) {
        return this.costoCIneMapper.toCostoCineList(
                this.costoCineRepository.findAllByIdCine_Id(idCine)
        );
    }
}
