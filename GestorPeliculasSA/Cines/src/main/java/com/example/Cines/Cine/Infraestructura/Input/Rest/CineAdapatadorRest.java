package com.example.Cines.Cine.Infraestructura.Input.Rest;

import com.example.Cines.Cine.Aplicacion.CasoUso.CrearCine.CrearCineDTO;
import com.example.Cines.Cine.Aplicacion.CasoUso.EditarCine.EditarCineDto;
import com.example.Cines.Cine.Aplicacion.Ports.Input.CrearCineInputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Input.EditarCineInputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Input.ListarCineEspecificoInputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Input.ListarCinesInputPort;
import com.example.Cines.Cine.Infraestructura.Input.Rest.Mapper.CineRestMapper;
import com.example.Cines.Cine.Infraestructura.Input.Rest.Models.Response.ResponseCineDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/cine")
public class CineAdapatadorRest {
    private final CineRestMapper cineRestMapper;
    private final CrearCineInputPort crearCineInputPort;
    private final EditarCineInputPort editarCineInputPort;
    private final ListarCinesInputPort listarCinesInputPort;
    private final ListarCineEspecificoInputPort listarCineEspecificoInputPort;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<ResponseCineDTO> crearCine(@Valid @RequestBody CrearCineDTO crearCineDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.cineRestMapper.toReponseCineDto(
                        crearCineInputPort.createCine((crearCineDTO))
                ));
    }

    @GetMapping("")
    public List<ResponseCineDTO> listadoCines() {
        return this.cineRestMapper.toReponseCineDtoList(this.listarCinesInputPort.listarCines()) ;
    }


    @GetMapping("/{id}")
    public ResponseCineDTO listadoCineEspecifico(@PathVariable UUID id) {
        return this.cineRestMapper.toReponseCineDto(this.listarCineEspecificoInputPort.listarCineEspecifico(id)) ;
    }

    @PutMapping("/{id}")
    public ResponseCineDTO EditarCineEspecifico(@PathVariable UUID id, @Valid @RequestBody EditarCineDto editarCineDto) {

              return   this.cineRestMapper.toReponseCineDto(
                      this.editarCineInputPort.editarCine(id, editarCineDto));

    }

}

