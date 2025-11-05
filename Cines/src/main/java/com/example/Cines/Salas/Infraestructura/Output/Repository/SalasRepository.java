package com.example.Cines.Salas.Infraestructura.Output.Repository;

import com.example.Cines.Salas.Infraestructura.Output.Entity.SalasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SalasRepository extends JpaRepository<SalasEntity, UUID> {
    List<SalasEntity> findAllByIdCine_Id(UUID idCineId);

}
