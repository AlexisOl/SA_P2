package com.example.Cines.Snacks.Infraestructura.Output.Entity;

import com.example.Cines.Cine.Infraestructura.Output.Entity.CineEntity;
import com.example.Cines.Snacks.Dominio.TipoSnacks;
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
@Table(name = "snacks")
public class SnacksEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private Double precio;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoSnacks tipo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cine", referencedColumnName = "id", nullable = false)
    private CineEntity idCine;
}
