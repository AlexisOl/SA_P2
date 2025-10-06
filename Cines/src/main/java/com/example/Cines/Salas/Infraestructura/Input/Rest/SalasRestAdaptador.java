package com.example.Cines.Salas.Infraestructura.Input.Rest;

import com.example.Cines.Salas.Aplicacion.CasoUso.CrearSala.CrearSalaDTO;
import com.example.Cines.Salas.Aplicacion.Ports.Input.CrearSalaEnCineInputPort;
import com.example.Cines.Salas.Aplicacion.Ports.Input.EditarSalaInputPort;
import com.example.Cines.Salas.Aplicacion.Ports.Input.ListarSalaEspecificaInputPort;
import com.example.Cines.Salas.Aplicacion.Ports.Input.ListarSalasPorCineInputPort;
import com.example.Cines.Salas.Infraestructura.Input.Rest.Mapper.SalasRestMapper;
import com.example.Cines.Salas.Infraestructura.Input.Rest.Models.ResponseSalasDTO;
import com.example.Cines.Snacks.Aplicacion.CasoUso.CrearSnacks.CrearSnacksDTO;
import com.example.Cines.Snacks.Aplicacion.Ports.Input.CrearSnackInputPort;
import com.example.Cines.Snacks.Aplicacion.Ports.Input.ListarSnacksCineInputPort;
import com.example.Cines.Snacks.Aplicacion.Ports.Input.ListarSnacksEspecificasInputPort;
import com.example.Cines.Snacks.Infraestructura.Input.Rest.Mapper.SnacksRestMapper;
import com.example.Cines.Snacks.Infraestructura.Input.Rest.Models.ResponseSnacksDTO;
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
@RequestMapping("/salas")
public class SalasRestAdaptador {
    private final SalasRestMapper salasRestMapper;
    private final CrearSalaEnCineInputPort crearSalaEnCineInputPort;
    private final ListarSalaEspecificaInputPort listarSalaEspecificaInputPort;
    private final ListarSalasPorCineInputPort listarSalasPorCineInputPort;
    //private final EditarSalaInputPort editarSalaInputPort;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<ResponseSalasDTO> crearSalas(@Valid @RequestBody CrearSalaDTO crearSalaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.salasRestMapper.toResponseSalasDTO(
                        crearSalaEnCineInputPort.crearSalaEnCine((crearSalaDTO))
                ));
    }

    @GetMapping("/cine/{idCine}")
    public List<ResponseSalasDTO> listadoSalasPorCine(@PathVariable("idCine") UUID idCine) {
        return this.salasRestMapper.toResponseSalasDTOList(this.listarSalasPorCineInputPort.ListarSalasPorCine(idCine)) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSalasDTO> listadoSalasEspecifica(@PathVariable("id")  UUID id) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(this.salasRestMapper.toResponseSalasDTO(this.listarSalaEspecificaInputPort.listarSalaEspecifica(id))) ;
    }
}
