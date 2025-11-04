package com.example.Cines.Salas.Dominio.ObjetosValor;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class MatrizAsientos {
    private Long filas;
    private Long columnas;


    public MatrizAsientos(Long filas, Long columnas) {
        if(filas==null || columnas==null){
            throw new IllegalArgumentException("No puede ingresar valores inexistentes");
        }

        if (filas<=0 || columnas<=0){
            throw new IllegalArgumentException("No puede ingresar valores negativos");
        }

        this.filas = filas;
        this.columnas = columnas;
    }
}
