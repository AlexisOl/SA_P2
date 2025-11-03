package com.example.Cines.BloqueoAnuncio.Infraestructura.Output.Repository;

import com.example.Cines.BloqueoAnuncio.Dominio.BloqueoAnuncio;
import com.example.Cines.BloqueoAnuncio.Infraestructura.Output.Entity.BloqueoAnuncioEntity;
import com.example.Cines.Cine.Infraestructura.Output.Entity.CineEntity;
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



    @Query(
            "SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END "+
                    "FROM BloqueoAnuncioEntity b "+
                    "WHERE b.idCine.id = :cine "+
                    "AND :fecha between b.fecha and b.fecha_fin"
    )
    boolean existeBloqueCineActualemente(@Param("cine") UUID cine,
                                         @Param("fecha") LocalDate fecha);


    @Query("SELECT b " +
            "FROM BloqueoAnuncioEntity b " +
            "where (" +
            "   (:fechaInicio BETWEEN b.fecha AND b.fecha_fin) OR " +
            "   (:fechaFinal BETWEEN b.fecha AND b.fecha_fin) OR " +
            "   (b.fecha BETWEEN :fechaInicio AND :fechaFinal) OR " +
            "   (b.fecha_fin BETWEEN :fechaInicio AND :fechaFinal)" +
            ")")
    List<BloqueoAnuncioEntity> cineBloqueoEnFechaDeterminada(
                                      @Param("fechaInicio") LocalDate fechaInicio,
                                      @Param("fechaFinal") LocalDate fechaFinal
    );


    @Query(value = """

            SELECT
            dias_solapados,
            dias_rango_a,
            dias_rango_a -dias_solapados AS dias_cubiertos,
            precio
            from (
                                    SELECT 
                    GREATEST(0, DATEDIFF(LEAST(b.fecha_fin, :fechaFinal), GREATEST(b.fecha, :fechaInicio))) AS dias_solapados,
                    DATEDIFF(:fechaFinal, :fechaInicio) AS dias_rango_a,
                    (select c.costo_ocultacion from cinema_db.cine c where c.id = b.id_cine)      as precio
                FROM cinema_db.bloqueo_anuncio_cine b
                WHERE b.id_cine = :cine
                ) AS calculo

    """, nativeQuery = true)
    Object determinaDiasSolapados(
            @Param("cine") UUID cine,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFinal") LocalDate fechaFinal
    );

}
