package com.example.Cines.CostosCine.Infraestructura.Output;

import com.example.Cines.CostosCine.Aplicacion.Ports.Output.CrearCostoCIneOutputPort;
import com.example.Cines.CostosCine.Dominio.CostoCine;
import com.example.Cines.CostosCine.Infraestructura.Output.Mapper.CostoCIneMapper;
import com.example.Cines.CostosCine.Infraestructura.Output.Repository.CostoCineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CostoCInePersistenciaAdapatador implements CrearCostoCIneOutputPort {
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
}
