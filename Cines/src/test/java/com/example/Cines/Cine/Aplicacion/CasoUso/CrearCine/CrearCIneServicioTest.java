package com.example.Cines.Cine.Aplicacion.CasoUso.CrearCine;

import com.example.Cines.Cine.Aplicacion.Ports.Output.CrearCineOutputPort;
import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.CostosCine.Aplicacion.Ports.Output.CrearCostoCIneOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CrearCIneServicioTest {
    private CrearCineOutputPort crearCineOutputPort;
    private CrearCostoCIneOutputPort crearCostoCIneOutputPort;
    private CrearCIneServicio crearCIneServicio;

    @BeforeEach
    void setUp() {
        crearCineOutputPort = mock(CrearCineOutputPort.class);
        crearCostoCIneOutputPort= mock(CrearCostoCIneOutputPort.class);
        crearCIneServicio = new CrearCIneServicio(crearCineOutputPort, crearCostoCIneOutputPort);
    }

    @Test
    void createCine_deberiaLlamarAlOutputPortYRetornarCine() {
        // Arrange
        CrearCineDTO dto = new CrearCineDTO("Cine Lux", "Zona 1", "12345678", 1500.0);

        Cine cineGuardado = new Cine(UUID.randomUUID(), "Cine Lux", "Zona 1", "12345678", LocalDate.now());
        when(crearCineOutputPort.createCine(any(Cine.class))).thenReturn(cineGuardado);

        // Act
        Cine result = crearCIneServicio.createCine(dto);

        // Assert
        ArgumentCaptor<Cine> captor = ArgumentCaptor.forClass(Cine.class);
        verify(crearCineOutputPort, times(1)).createCine(captor.capture());

        Cine cineEnviado = captor.getValue();

        assertThat(cineEnviado.getNombre()).isEqualTo("Cine Lux");
        assertThat(cineEnviado.getUbicacion()).isEqualTo("Zona 1");
        assertThat(cineEnviado.getTelefono()).isEqualTo("12345678");
        assertThat(cineEnviado.getId()).isNotNull();
        assertThat(cineEnviado.getFechaCreacion()).isEqualTo(LocalDate.now());

        assertThat(result).isEqualTo(cineGuardado);
    }
}