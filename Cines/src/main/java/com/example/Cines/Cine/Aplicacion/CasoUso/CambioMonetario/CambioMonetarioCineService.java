package com.example.Cines.Cine.Aplicacion.CasoUso.CambioMonetario;

import com.example.Cines.Cine.Aplicacion.Ports.Input.CambioMonetarioInputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Output.CambioMonetarioOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CambioMonetarioCineService implements CambioMonetarioInputPort {

    private CambioMonetarioOutputPort  cambioMonetarioOutputPort;

    public CambioMonetarioCineService(CambioMonetarioOutputPort cambioMonetarioOutputPort) {
        this.cambioMonetarioOutputPort = cambioMonetarioOutputPort;
    }


    @Override
    public Boolean cambioMonetario(UUID id, Double cantidad, Boolean estadoCambio) {

        return this.cambioMonetarioOutputPort.cambioMonetario(id, cantidad, estadoCambio);
    }
}
