package com.example.Cines.CostosCine.Infraestructura.Input.Rest;

import com.example.Cines.Cine.Aplicacion.CasoUso.CrearCine.CrearCineDTO;
import com.example.Cines.Cine.Infraestructura.Input.Rest.Models.Response.ResponseCineDTO;
import com.example.Cines.CostosCine.Aplicacion.CasoUso.CrearCostoCIne.CrearCostosCineDTO;
import com.example.Cines.CostosCine.Aplicacion.Ports.Input.CrearCostoCineInputPort;
import com.example.Cines.CostosCine.Aplicacion.Ports.Input.ListarCostosCineEspecificoInputPort;
import com.example.Cines.CostosCine.Infraestructura.Input.Rest.Mapper.CostoCineRestMapper;
import com.example.Cines.CostosCine.Infraestructura.Input.Rest.Models.ResponseCostoCineDTO;
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
@RequestMapping("/costoCine")
public class CostoCineRestAdaptador {

    private final CostoCineRestMapper costoCineRestMapper;
    private final ListarCostosCineEspecificoInputPort listarCostosCineEspecificoInputPort;
    private final CrearCostoCineInputPort crearCostoCineInputPort;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<ResponseCostoCineDTO> crearCostoCine(@Valid @RequestBody CrearCostosCineDTO crearCostosCineDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.costoCineRestMapper.toReponseCineDto(
                        crearCostoCineInputPort.crearCostoCine((crearCostosCineDTO))
                ));
    }

    @GetMapping("/cine/{idCine}")
    public List<ResponseCostoCineDTO> listadoCostosPorCine(@PathVariable("idCine")  UUID idCine) {
        return this.costoCineRestMapper.toReponseCineDtoList(this.listarCostosCineEspecificoInputPort.listarCostosCineEspecifico(idCine)) ;
    }
}
