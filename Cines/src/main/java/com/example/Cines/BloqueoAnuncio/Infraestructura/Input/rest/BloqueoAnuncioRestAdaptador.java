package com.example.Cines.BloqueoAnuncio.Infraestructura.Input.rest;

import com.example.Cines.BloqueoAnuncio.Aplicacion.CasosUso.CrearBloqueoAnuncios.CrearBloqueoAnuncioDTO;
import com.example.Cines.BloqueoAnuncio.Aplicacion.CasosUso.CrearBloqueoAnuncios.CrearBloqueoAnuncioService;
import com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Input.CineConBloqueoActualmenteInputPort;
import com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Input.CinesAnuncioBloqueadoFechaInputPort;
import com.example.Cines.BloqueoAnuncio.Dominio.BloqueoAnuncio;
import com.example.Cines.BloqueoAnuncio.Infraestructura.Input.rest.Mapper.BloqueoAnuncioRestMapper;
import com.example.Cines.BloqueoAnuncio.Infraestructura.Input.rest.Model.ResponseBloqueoAnuncioDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/bloqueoAnuncios")
public class BloqueoAnuncioRestAdaptador {

    private final BloqueoAnuncioRestMapper bloqueoAnuncioRestMapper;
    private final CrearBloqueoAnuncioService bloqueoAnuncioService;
    private final CineConBloqueoActualmenteInputPort cineConBloqueoActualmenteInputPort;
    private final CinesAnuncioBloqueadoFechaInputPort cinesAnuncioBloqueadoFechaInputPort;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<ResponseBloqueoAnuncioDTO> crearBloqueoAnuncio(@Valid @RequestBody CrearBloqueoAnuncioDTO crearBloqueoAnuncioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.bloqueoAnuncioRestMapper.toResponseBloqueoAnuncioDto(
                        bloqueoAnuncioService.CrearBloqueoAnuncio(crearBloqueoAnuncioDTO)
                ));
    }

    @GetMapping("/verBloqueoActual/{id}")
    @Transactional
    public Boolean bloqueoCineActualemente(@PathVariable("id") UUID id) {
        return (this.cineConBloqueoActualmenteInputPort.cineConBloqueo(id)) ;
    }

    @GetMapping("/verBloqueo/{fecha_inicio}/{fecha_fin}")
    @Transactional
    public List<BloqueoAnuncio> bloqueosCineENFechaDeterminada(@PathVariable("fecha_inicio") LocalDate fecha_inicio, @PathVariable("fecha_fin") LocalDate fecha_fin) {
        return (this.cinesAnuncioBloqueadoFechaInputPort.listaCinesAnunciosBloqueados(fecha_inicio, fecha_fin)) ;
    }

}
