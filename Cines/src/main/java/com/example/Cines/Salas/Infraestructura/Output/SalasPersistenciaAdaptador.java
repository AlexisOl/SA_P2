package com.example.Cines.Salas.Infraestructura.Output;

import com.example.Cines.Salas.Aplicacion.Ports.Output.*;
import com.example.Cines.Salas.Dominio.Salas;
import com.example.Cines.Salas.Infraestructura.Output.Entity.SalasEntity;
import com.example.Cines.Salas.Infraestructura.Output.Mapper.SalasMapper;
import com.example.Cines.Salas.Infraestructura.Output.Repository.SalasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class SalasPersistenciaAdaptador implements CrearSalaEnCineOutputPort, EditarSalaOutputPort, ListarSalaEspecificaOutputPort, ListarSalasPorCineOutputPort,
        CambiarVisibildadOutputPort, CambiarVisibildadComentariosOutputPort , CambiarVisibildadCalificacionesOutputPort{

    private final SalasRepository salasRepository;
    private final SalasMapper   salasMapper;

    @Override
    @Transactional

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
    @Transactional

    public Salas listarSalaEspecifica(UUID id) {
        Salas sala = this.salasMapper.toSalas(
                this.salasRepository.findById(id).get()
        );
        return sala;
    }

    @Override
    @Transactional

    public void cambiarVisbilidad(UUID id) {
        SalasEntity entidad = this.salasRepository.findById(id).orElse(null);
        if (entidad == null) return;
        entidad.setVisible(!entidad.isVisible());
        this.salasRepository.save(entidad);

    }

    @Override
    @Transactional

    public void cambiarVisbilidadComentarios(UUID id) {
        SalasEntity entidad = this.salasRepository.findById(id).orElse(null);
        if (entidad == null) return;
        entidad.setValidarComnentarios(!entidad.isValidarComnentarios());
        this.salasRepository.save(entidad);

    }

    @Override
    @Transactional
    public void cambiarVisbilidadCalificaciones(UUID id) {
        System.out.println(id);
        SalasEntity entidad = this.salasRepository.findById(id).orElse(null);
        System.out.println("aca2");

        if (entidad == null) return;
        System.out.println(!entidad.isValidarCalificaciones());
        System.out.println("aca3"+ entidad.getNombre());

        entidad.setValidarCalificaciones(!entidad.isValidarCalificaciones());
        this.salasRepository.save(entidad);

    }
}
