package com.example.Cines.Snacks.Infraestructura.Output.Repository;

import com.example.Cines.Snacks.Dominio.Snacks;
import com.example.Cines.Snacks.Infraestructura.Output.Entity.SnacksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SnacksRepository extends JpaRepository<SnacksEntity, UUID> {
    List<SnacksEntity> findAllByIdCine_Id(UUID idCineId);
}
