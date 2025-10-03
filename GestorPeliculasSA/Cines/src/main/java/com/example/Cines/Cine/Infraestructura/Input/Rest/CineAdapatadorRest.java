package com.example.Cines.Cine.Infraestructura.Input.Rest;

import com.example.Cines.Cine.Aplicacion.CasoUso.CrearCine.CrearCineDTO;
import com.example.Cines.Cine.Aplicacion.Ports.Input.CrearCineInputPort;
import com.example.Cines.Cine.Infraestructura.Input.Rest.Mapper.CineRestMapper;
import com.example.Cines.Cine.Infraestructura.Input.Rest.Models.Response.ResponseCineDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/cine")
public class CineAdapatadorRest {
    private final CineRestMapper cineRestMapper;
    private final CrearCineInputPort crearCineInputPort;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<ResponseCineDTO> crearCine(@Valid @RequestBody CrearCineDTO crearCineDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.cineRestMapper.toReponseCineDto(
                        crearCineInputPort.createCine((crearCineDTO))
                ));
    }
}
