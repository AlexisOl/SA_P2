package com.example.Cines.Cine.Infraestructura.Kafka.DTO.DiasDescuentoAnunciosBloqueados;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiasDescuentoAnunciosBloqueados {
    UUID cine;
    Long diasSolapados;
    Long diasRango ;
    Long diasCubiertos;
    Double precio;
    boolean estado;
}
