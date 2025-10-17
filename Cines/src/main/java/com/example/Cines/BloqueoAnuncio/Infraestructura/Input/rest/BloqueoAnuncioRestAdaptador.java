package com.example.Cines.BloqueoAnuncio.Infraestructura.Input.rest;

import com.example.Cines.BloqueoAnuncio.Aplicacion.CasosUso.CrearBloqueoAnuncios.CrearBloqueoAnuncioDTO;
import com.example.Cines.BloqueoAnuncio.Aplicacion.CasosUso.CrearBloqueoAnuncios.CrearBloqueoAnuncioService;
import com.example.Cines.BloqueoAnuncio.Infraestructura.Input.rest.Mapper.BloqueoAnuncioRestMapper;
import com.example.Cines.BloqueoAnuncio.Infraestructura.Input.rest.Model.ResponseBloqueoAnuncioDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/bloqueoAnuncios")
public class BloqueoAnuncioRestAdaptador {

    private final BloqueoAnuncioRestMapper bloqueoAnuncioRestMapper;
    private final CrearBloqueoAnuncioService bloqueoAnuncioService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<ResponseBloqueoAnuncioDTO> crearBloqueoAnuncio(@Valid @RequestBody CrearBloqueoAnuncioDTO crearBloqueoAnuncioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.bloqueoAnuncioRestMapper.toResponseBloqueoAnuncioDto(
                        bloqueoAnuncioService.CrearBloqueoAnuncio(crearBloqueoAnuncioDTO)
                ));
    }

}
