package com.example.Cines.Cine.Aplicacion.CasoUso.ListarCinesQueNoEstanBloqueados;

import com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Input.CinesAnuncioBloqueadoFechaInputPort;
import com.example.Cines.BloqueoAnuncio.Dominio.BloqueoAnuncio;
import com.example.Cines.Cine.Aplicacion.Ports.Input.ListarCinesQueNoEstanBloqueadosInputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Output.ListarCinesQueNoEstanBloqueadosOutputPort;
import com.example.Cines.Cine.Dominio.Cine;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ListarCinesQueNoEstanBloqueadosService implements ListarCinesQueNoEstanBloqueadosInputPort {

    private final ListarCinesQueNoEstanBloqueadosOutputPort listarCinesQueNoEstanBloqueadosOutputPort;
    private final CinesAnuncioBloqueadoFechaInputPort cinesAnuncioBloqueadoFechaInputPort;

    public ListarCinesQueNoEstanBloqueadosService(ListarCinesQueNoEstanBloqueadosOutputPort listarCinesQueNoEstanBloqueadosOutputPort,
                                                  CinesAnuncioBloqueadoFechaInputPort cinesAnuncioBloqueadoFechaInputPort) {
        this.listarCinesQueNoEstanBloqueadosOutputPort = listarCinesQueNoEstanBloqueadosOutputPort;
        this.cinesAnuncioBloqueadoFechaInputPort=cinesAnuncioBloqueadoFechaInputPort;
    }
    @Override
    public List<Cine> listaCinesAnunciosNoBloqueados(LocalDate fecha, LocalDate fechaFin) {
        List<BloqueoAnuncio> listado =this.cinesAnuncioBloqueadoFechaInputPort.listaCinesAnunciosBloqueados(fecha, fechaFin);


        //aca
        System.out.println("Lista de cines anuncios bloqueados");
        List<UUID> listadoId = listado.stream()
                .map(x ->
                        x.getIdCine().getId()
                )
                .toList();

        for(UUID id : listadoId){
            System.out.println(id);
        }

        return this.listarCinesQueNoEstanBloqueadosOutputPort.listaCinesAnunciosNoBloqueados(listadoId);
    }
}
