package com.example.Cines.Cine.Infraestructura.Output.Repository;

import com.example.Cines.Cine.Infraestructura.Output.Entity.CineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CineRepository extends JpaRepository<CineEntity, UUID> {
}
