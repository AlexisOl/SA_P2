package com.example.Cines.Cine.Infraestructura.Kafka;

import com.example.Cines.Cine.Aplicacion.Ports.Input.CambioMonetarioInputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Input.ExisteCineInputPort;
import com.example.Cines.Cine.Infraestructura.Kafka.DTO.EstadoMonetarioAnuncioComprado.AnuncioCreadoDTO;
import com.example.Cines.Cine.Infraestructura.Kafka.DTO.EstadoMonetarioAnuncioComprado.AnuncioFallidoDTO;
import com.example.Cines.Cine.Infraestructura.Kafka.DTO.EstadoMonetarioAnuncioComprado.CineActualizadoDTO;
import com.example.Cines.Cine.Infraestructura.Kafka.DTO.VerficarCIne.VerificarCineDTO;
import com.example.Cines.Cine.Infraestructura.Kafka.DTO.VerficarCIne.VerificarCineRespuestaDTO;
import com.example.comun.DTO.FacturaBoleto.CobroCineDTO;
import com.example.comun.DTO.FacturaBoleto.FacturaBoletoCreadoDTO;
import com.example.comun.DTO.FacturaBoleto.RespuestaFacturaBoletoCreadoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.messaging.Message;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class CineAdaptadorKafka {
    private final ExisteCineInputPort existeCineInputPort;
    private final CambioMonetarioInputPort cambioMonetarioInputPort;



    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public CineAdaptadorKafka(ExisteCineInputPort existeCineInputPort,
                              CambioMonetarioInputPort cambioMonetarioInputPort,
                            KafkaTemplate<String, String> kafkaTemplate,
                            ObjectMapper objectMapper) {
        this.existeCineInputPort = existeCineInputPort;
        this.kafkaTemplate = kafkaTemplate;
        this.cambioMonetarioInputPort=cambioMonetarioInputPort;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "verificar-cine", groupId = "cines-group")
    public void verificarCine(@Payload String mensaje, @Header(KafkaHeaders.CORRELATION_ID) String correlationId) throws Exception {
        // Deserializar el mensaje
        VerificarCineDTO solicitud = objectMapper.readValue(mensaje, VerificarCineDTO.class);

        // Invocar el caso de uso
        boolean existe = existeCineInputPort.existeCineEspecifico(solicitud.getIdCine());

        // Crear respuesta
        VerificarCineRespuestaDTO respuesta = new VerificarCineRespuestaDTO();
        respuesta.setExiste(existe);
        respuesta.setCorrelationId(correlationId);

        // Serializar y enviar respuesta con correlationId header
        String respuestaMensaje = objectMapper.writeValueAsString(respuesta);
        Message<String> kafkaMessage = MessageBuilder
                .withPayload(respuestaMensaje)
                .setHeader(KafkaHeaders.TOPIC, "respuesta-verificar-cine")
                .setHeader(KafkaHeaders.CORRELATION_ID, correlationId)
                .build();

        kafkaTemplate.send(kafkaMessage);
    }



    // para los anuncios comprados
    @KafkaListener(topics = "propiedad-anuncio-creado", groupId = "cines-group")
    @Transactional
    public void handlePropiedadAnuncioCreado(@Payload String mensaje, @Header(KafkaHeaders.CORRELATION_ID) String correlationId) throws Exception {
        AnuncioCreadoDTO evento = objectMapper.readValue(mensaje, AnuncioCreadoDTO.class);
        boolean exito = false;
        String motivoFallo = "Error desconocido";
        System.out.println("dinero: "+evento.getCosto()+" id: "+ evento.getIdCine());

        // Validate DTO
        if (evento.getIdCine() == null || evento.getCosto() <= 0) {
            motivoFallo = "Invalid cineId or costo: " + evento;
        } else if (!existeCineInputPort.existeCineEspecifico(evento.getIdCine())) {
            motivoFallo = "Cine does not exist: " + evento.getIdCine();
        } else {
            System.out.println("dinero: "+evento.getCosto());
            exito = cambioMonetarioInputPort.cambioMonetario(evento.getIdCine(), evento.getCosto(), true);
            if (!exito) {
                motivoFallo = "Failed to update cinema revenue";
            }
        }

        // Publish response
        if (exito) {
            CineActualizadoDTO respuesta = new CineActualizadoDTO();
            respuesta.setAnuncioId(evento.getAnuncioId());
            respuesta.setCorrelationId(correlationId);
            String respuestaMensaje = objectMapper.writeValueAsString(respuesta);
            Message<String> kafkaMessage = MessageBuilder
                    .withPayload(respuestaMensaje)
                    .setHeader(KafkaHeaders.TOPIC, "cine-actualizado")
                    .setHeader(KafkaHeaders.CORRELATION_ID, correlationId)
                    .build();
            kafkaTemplate.send(kafkaMessage);
        } else {
            System.out.println("aca falla-------------");
            System.out.println(evento.getAnuncioId());
            AnuncioFallidoDTO fallo = new AnuncioFallidoDTO();
            fallo.setAnuncioId(evento.getAnuncioId());
            fallo.setMotivoFallo(motivoFallo);
            fallo.setCorrelationId(correlationId);
            String falloMensaje = objectMapper.writeValueAsString(fallo);
            Message<String> kafkaMessage = MessageBuilder
                    .withPayload(falloMensaje)
                    .setHeader(KafkaHeaders.TOPIC, "propiedad-anuncio-fallido")
                    .setHeader(KafkaHeaders.CORRELATION_ID, correlationId)
                    .build();
            kafkaTemplate.send(kafkaMessage);
        }
    }

    // para la facturacion
    @KafkaListener(topics = "propiedad-facturacion-generada", groupId = "cines-group")
    @Transactional
    public void handlePropiedadFacturacionCreada(@Payload String mensaje, @Header(KafkaHeaders.CORRELATION_ID) String correlationId) throws Exception {
        CobroCineDTO evento = objectMapper.readValue(mensaje, CobroCineDTO.class);
        boolean exito = false;
        String motivoFallo = "Error desconocido";
        System.out.println("dinero: "+evento.getCosto()+" id: "+ evento.getIdCine());

        // Validate DTO
        if (evento.getIdCine() == null || evento.getCosto() <= 0) {
            motivoFallo = "Costo o cine invalido: " + evento;
        } else if (!existeCineInputPort.existeCineEspecifico(evento.getIdCine())) {
            motivoFallo = "Cine no existe: " + evento.getIdCine();
        } else {
            System.out.println("dinero: "+evento.getCosto());
            exito = cambioMonetarioInputPort.cambioMonetario(evento.getIdCine(), evento.getCosto(), true);
            if (!exito) {
                motivoFallo = "NO se pudo actualizar";
            }
        }


        RespuestaFacturaBoletoCreadoDTO respuesta = new RespuestaFacturaBoletoCreadoDTO();
        respuesta.setMotivoFallo(motivoFallo);
        respuesta.setCorrelationId(evento.getCorrelationId());
        respuesta.setExito(exito);
        respuesta.setVentaId(evento.getVentaId());

        String json = objectMapper.writeValueAsString(respuesta);
        String topic = exito ? "venta-actualizada" : "venta-fallido";
        kafkaTemplate.send(topic, json);

    }

    public RespuestaFacturaBoletoCreadoDTO respuestaGenerada(UUID ventaId, boolean exito, String motivoFallo, String correlationId) {
        RespuestaFacturaBoletoCreadoDTO nuevaRespuesta = new RespuestaFacturaBoletoCreadoDTO();
        nuevaRespuesta.setMotivoFallo(motivoFallo);
        nuevaRespuesta.setCorrelationId(correlationId);
        nuevaRespuesta.setExito(exito);
        nuevaRespuesta.setVentaId(ventaId);
        return nuevaRespuesta;
    }

}
