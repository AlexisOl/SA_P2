package com.example.Cines.CostosCine.Infraestructura.Output.Repository;

import com.example.Cines.CostosCine.Infraestructura.Output.Entity.CostoCineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CostoCineRepository extends JpaRepository<CostoCineEntity, Long> {
    List<CostoCineEntity> findAllByIdCine_Id(UUID idCineId);


    @Query(value = """
            WITH RECURSIVE meses AS (
              SELECT DATE_FORMAT(MIN(fecha), '%Y-%m-01') AS mes_inicio,
                     LAST_DAY(CURDATE()) AS mes_fin
              FROM cinema_db.costo_cine
              WHERE id_cine = :cineId
            
              UNION ALL
            
              SELECT DATE_FORMAT(DATE_ADD(mes_inicio, INTERVAL 1 MONTH), '%Y-%m-01'),
                     mes_fin
              FROM meses
              WHERE DATE_ADD(mes_inicio, INTERVAL 1 MONTH) <= mes_fin
            )
            SELECT\s
              DATE_FORMAT(m.mes_inicio, '%m/%Y') AS mes,
              (
                SELECT c.costo
                FROM cinema_db.costo_cine c
                WHERE c.id_cine = :cineId
                  AND c.fecha <= LAST_DAY(m.mes_inicio)
                ORDER BY c.fecha DESC
                LIMIT 1
              ) AS costo_vigente,
              (
                (
                  SELECT c.costo
                  FROM cinema_db.costo_cine c
                  WHERE c.id_cine = :cineId
                    AND c.fecha <= LAST_DAY(m.mes_inicio)
                  ORDER BY c.fecha DESC
                  LIMIT 1
                ) * 30
              ) AS costo_mensual
            FROM meses m
            WHERE m.mes_inicio <= LAST_DAY(CURDATE())
            ORDER BY m.mes_inicio;

""", nativeQuery = true)
    List<Object[]> determinaPagosMensuales(@Param("cineId") UUID cineId);

}
