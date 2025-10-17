package com.example.Cines.Cine.Aplicacion.Ports.Input;

import java.util.UUID;

public interface CambioMonetarioInputPort {
    Boolean cambioMonetario(UUID id, Double cantidad, Boolean estadoCambio);

}
