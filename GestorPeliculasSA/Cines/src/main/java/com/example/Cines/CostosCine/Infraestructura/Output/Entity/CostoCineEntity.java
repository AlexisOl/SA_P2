package com.example.Cines.CostosCine.Infraestructura.Output.Entity;

import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.Cine.Infraestructura.Output.Entity.CineEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "costo_cine")
public class CostoCineEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(nullable = false)
    private LocalDate fecha;
    @Column(nullable = false)
    private Double costo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cine", referencedColumnName = "id", nullable = false)
    private CineEntity idCine;
}
