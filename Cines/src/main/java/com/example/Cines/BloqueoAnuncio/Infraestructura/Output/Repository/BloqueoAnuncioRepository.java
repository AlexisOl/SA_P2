package com.example.Cines.BloqueoAnuncio.Infraestructura.Output.Repository;

import com.example.Cines.BloqueoAnuncio.Dominio.BloqueoAnuncio;
import com.example.Cines.BloqueoAnuncio.Infraestructura.Output.Entity.BloqueoAnuncioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BloqueoAnuncioRepository extends JpaRepository<BloqueoAnuncioEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END " +
            "FROM BloqueoAnuncioEntity b " +
            "WHERE b.idCine.id = :cine " +
            "AND (" +
            "   (:fechaInicio BETWEEN b.fecha AND b.fecha_fin) OR " +
            "   (:fechaFinal BETWEEN b.fecha AND b.fecha_fin) OR " +
            "   (b.fecha BETWEEN :fechaInicio AND :fechaFinal) OR " +
            "   (b.fecha_fin BETWEEN :fechaInicio AND :fechaFinal)" +
            ")")
    boolean existeBloqueoPrevioENCine(@Param("cine") UUID cine,
                                       @Param("fechaInicio") LocalDate fechaInicio,
                                       @Param("fechaFinal") LocalDate fechaFinal);

    List<BloqueoAnuncioEntity> findAllByIdCine_Id(UUID idCine);
}
