package com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Input;


import com.example.Cines.BloqueoAnuncio.Aplicacion.CasosUso.CrearBloqueoAnuncios.CrearBloqueoAnuncioDTO;
import com.example.Cines.BloqueoAnuncio.Dominio.BloqueoAnuncio;

public interface CrearBloqueoAnunciosCineInputPort {
    BloqueoAnuncio CrearBloqueoAnuncio(CrearBloqueoAnuncioDTO crearBloqueoAnuncioDTO);
}
