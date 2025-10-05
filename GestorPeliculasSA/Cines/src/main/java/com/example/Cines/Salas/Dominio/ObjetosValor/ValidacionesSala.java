package com.example.Cines.Salas.Dominio.ObjetosValor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidacionesSala {
    private boolean validarComnentarios;
    private boolean validarCalificaciones;
    private boolean visible;

    public ValidacionesSala(boolean validarComnentarios, boolean validarCalificaciones, boolean visible) {
        this.validarComnentarios = validarComnentarios;
        this.validarCalificaciones = validarCalificaciones;
        this.visible = visible;
    }
}
