package com.example.Cines.Salas.Infraestructura.Output.Entity;

import com.example.Cines.Cine.Infraestructura.Output.Entity.CineEntity;
import com.example.Cines.Salas.Dominio.TipoSala;
import com.example.Cines.Snacks.Dominio.TipoSnacks;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "salas")
public class SalasEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String nombre;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoSala tipoSala;
    @Column(nullable = false)
    private Long filas;
    @Column(nullable = false)
    private Long columnas;

    @Column(name = "validar_comnentarios",nullable = false)
    private boolean validarComnentarios;
    @Column(name = "validar_calificaciones",nullable = false)
    private boolean validarCalificaciones;
    @Column(nullable = false)
    private boolean visible;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cine", referencedColumnName = "id", nullable = false)
    private CineEntity idCine;
}
