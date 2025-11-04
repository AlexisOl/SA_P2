package com.example.Cines.Salas.Infraestructura.Output;

import com.example.Cines.Cine.Aplicacion.Ports.Output.ListarCineEspecificoOutputPort;
import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.Salas.Aplicacion.Ports.Output.CrearSalaEnCineOutputPort;
import com.example.Cines.Salas.Aplicacion.Ports.Output.EditarSalaOutputPort;
import com.example.Cines.Salas.Aplicacion.Ports.Output.ListarSalaEspecificaOutputPort;
import com.example.Cines.Salas.Aplicacion.Ports.Output.ListarSalasPorCineOutputPort;
import com.example.Cines.Salas.Dominio.Salas;
import com.example.Cines.Salas.Infraestructura.Output.Mapper.SalasMapper;
import com.example.Cines.Salas.Infraestructura.Output.Repository.SalasRepository;
import com.sun.jdi.PrimitiveValue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class SalasPersistenciaAdaptador implements CrearSalaEnCineOutputPort, EditarSalaOutputPort, ListarSalaEspecificaOutputPort, ListarSalasPorCineOutputPort {

    private final SalasRepository salasRepository;
    private final SalasMapper   salasMapper;

    @Override
    public Salas crearSalaEnCine(Salas salas) {
        System.out.println(salas.toString());
        return this.salasMapper.toSalas(
                this.salasRepository.save(
                        this.salasMapper.toSalasEntity(salas)
                )
        );
    }


    @Override
    public Salas editarSala( Salas salas) {
        return this.salasMapper.toSalas(
                this.salasRepository.save(
                        this.salasMapper.toSalasEntity(salas)
                )
        );
    }

    @Override
    public List<Salas> ListarSalasPorCine(UUID id) {
        List<Salas> listados = this.salasMapper.toSalasList(
                this.salasRepository.findAllByIdCine_Id(id)
        );

        for (Salas salas : listados) {
            System.out.println(salas.getMatrizAsientos().getFilas()+ "--------"+ salas.getNombre());

        }
        return listados;

    }

    @Override
    public Salas listarSalaEspecifica(UUID id) {
        Salas sala = this.salasMapper.toSalas(
                this.salasRepository.findById(id).get()
        );
        return sala;
    }
}
