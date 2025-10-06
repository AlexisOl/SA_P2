package com.example.Cines.Snacks.Infraestructura.Input.Rest;

import com.example.Cines.CostosCine.Aplicacion.CasoUso.CrearCostoCIne.CrearCostosCineDTO;
import com.example.Cines.CostosCine.Aplicacion.Ports.Input.CrearCostoCineInputPort;
import com.example.Cines.CostosCine.Aplicacion.Ports.Input.ListarCostosCineEspecificoInputPort;
import com.example.Cines.CostosCine.Infraestructura.Input.Rest.Mapper.CostoCineRestMapper;
import com.example.Cines.CostosCine.Infraestructura.Input.Rest.Models.ResponseCostoCineDTO;
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
@RequestMapping("/snacks")
public class SnacksRestAdaptador {
    private final SnacksRestMapper snacksRestMapper;
    private final CrearSnackInputPort crearSnackInputPort;
    private final ListarSnacksCineInputPort listarSnacksCineInputPort;
    private final ListarSnacksEspecificasInputPort listarSnacksEspecificasInputPort;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<ResponseSnacksDTO> crearSnacks(@Valid @RequestBody CrearSnacksDTO  crearSnacksDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.snacksRestMapper.toResponseSnacksDTO(
                        crearSnackInputPort.CrearSnack((crearSnacksDTO))
                ));
    }

    @GetMapping("/cine/{idCine}")
    public List<ResponseSnacksDTO> listadoSnacksPorCine(@PathVariable("idCine")  UUID idCine) {
        return this.snacksRestMapper.toResponseSnacksDTOList(this.listarSnacksCineInputPort.ListarSnack(idCine)) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSnacksDTO> listadoCostosPorCine(@PathVariable("id")  UUID id) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(this.snacksRestMapper.toResponseSnacksDTO(this.listarSnacksEspecificasInputPort.ListarSnacksEspecificas(id))) ;
    }
}
