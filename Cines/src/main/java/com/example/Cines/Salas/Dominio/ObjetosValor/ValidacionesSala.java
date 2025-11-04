package com.example.Cines.Salas.Dominio.ObjetosValor;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ValidacionesSala {
    private boolean validar_comnentarios;
    private boolean validar_calificaciones;
    private boolean visible;

    public ValidacionesSala(boolean validar_comnentarios, boolean validar_calificaciones, boolean visible) {
        this.validar_comnentarios = validar_comnentarios;
        this.validar_calificaciones = validar_calificaciones;
        this.visible = visible;
    }
}
