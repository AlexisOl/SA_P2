package com.example.Cines.Cine.Infraestructura.Output.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "cine")
public class CineEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String ubicacion;
    @Column(nullable = false)
    private String telefono;
    @Column(nullable = false)
    private LocalDate fechaCreacion;
}
