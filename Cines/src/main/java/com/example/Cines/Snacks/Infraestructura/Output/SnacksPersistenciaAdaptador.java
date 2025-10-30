package com.example.Cines.Snacks.Infraestructura.Output;

import com.example.Cines.Cine.Aplicacion.Ports.Output.ListarCineEspecificoOutputPort;
import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.Snacks.Aplicacion.Ports.Output.CrearSnackOutputPort;
import com.example.Cines.Snacks.Aplicacion.Ports.Output.ListarSnacksCineOutputPort;
import com.example.Cines.Snacks.Aplicacion.Ports.Output.ListarSnacksEspecificasOutputPort;
import com.example.Cines.Snacks.Dominio.Snacks;
import com.example.Cines.Snacks.Infraestructura.Output.Mapper.SnacksMapper;
import com.example.Cines.Snacks.Infraestructura.Output.Repository.SnacksRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Component
@AllArgsConstructor
public class SnacksPersistenciaAdaptador implements CrearSnackOutputPort, ListarSnacksEspecificasOutputPort, ListarSnacksCineOutputPort {
    private final SnacksRepository snacksRepository;
    private final SnacksMapper snacksMapper;

    @Override
    @Transactional(readOnly = true)
    public Snacks ListarSnacksEspecificas(UUID id) {
        return this.snacksMapper.toSnacks(
                this.snacksRepository.findById(id).get()
        );
    }

    @Override
    @Transactional
    public Snacks CrearSnack(Snacks dto) {
        return this.snacksMapper.toSnacks(
                this.snacksRepository.save(this.snacksMapper.toSnacksEntity(dto))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Snacks> ListarSnack(UUID id) {
        return this.snacksMapper.toSnacksList(
                this.snacksRepository.findAllByIdCine_Id(id)
        );
    }
}
