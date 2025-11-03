package com.example.Cines.Cine.Aplicacion.Ports.Output;

import com.example.Cines.Cine.Dominio.Cine;

import java.util.List;
import java.util.UUID;

public interface ListarCinesQueNoEstanBloqueadosOutputPort {
    List<Cine> listaCinesAnunciosNoBloqueados(List<UUID> idCines);

}
