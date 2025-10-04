package com.example.Cines.CostosCine.Infraestructura.Output.Repository;

import com.example.Cines.CostosCine.Infraestructura.Output.Entity.CostoCineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostoCineRepository extends JpaRepository<CostoCineEntity, Long> {
}
