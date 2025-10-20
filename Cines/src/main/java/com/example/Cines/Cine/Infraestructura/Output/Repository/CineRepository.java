package com.example.Cines.Cine.Infraestructura.Output.Repository;

import com.example.Cines.Cine.Infraestructura.Output.Entity.CineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface CineRepository extends JpaRepository<CineEntity, UUID> {



    // validacion que al restar no se peuda
//    @Query("select count(*) from CineEntity  c where c.id = :idCine and c.cartera >0")
//    boolean sePuedeHacerTransaccion(
//            UUID idCine, boolean estadoCambio, double cantidad
//    );


    @Transactional
    @Modifying
    @Query("UPDATE CineEntity c SET c.cartera = c.cartera + :cantidad WHERE c.id = :idCine")
    int updateSaldo(@Param("idCine") UUID idCine, @Param("cantidad") double cantidad);

    default boolean ingresoSaldo(UUID idCine, boolean estadoCambio, double cantidad) {
        double adjustedCantidad = estadoCambio ? cantidad : -cantidad;

        int updatedRows = updateSaldo(idCine, adjustedCantidad);
        return updatedRows > 0;
    }


    // ahora deberia de ingresar en todos los cines que no esten bloqueados en fechas
    
}