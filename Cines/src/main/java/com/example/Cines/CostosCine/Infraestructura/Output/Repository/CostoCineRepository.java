package com.example.Cines.CostosCine.Infraestructura.Output.Repository;

import com.example.Cines.CostosCine.Infraestructura.Output.Entity.CostoCineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CostoCineRepository extends JpaRepository<CostoCineEntity, Long> {
    List<CostoCineEntity> findAllByIdCine_Id(UUID idCineId);


}
