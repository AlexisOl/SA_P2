package com.example.Cines.Salas.Aplicacion.CasoUso.CrearSala;

import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.Salas.Dominio.ObjetosValor.MatrizAsientos;
import com.example.Cines.Salas.Dominio.ObjetosValor.ValidacionesSala;
import com.example.Cines.Salas.Dominio.TipoSala;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearSalaDTO {

    private String nombre;
    private String tipoSala;
    private MatrizAsientos matrizAsientos;
    private ValidacionesSala validacionesSala;
    private UUID idCine;
}
