package com.example.Cines.Cine.Infraestructura.Kafka;

import com.example.Cines.Cine.Aplicacion.Ports.Input.ExisteCineInputPort;
import com.example.Cines.Cine.Infraestructura.Kafka.DTO.VerficarCIne.VerificarCineDTO;
import com.example.Cines.Cine.Infraestructura.Kafka.DTO.VerficarCIne.VerificarCineRespuestaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.messaging.Message;
@Component
public class CineAdaptadorKafka {
    private final ExisteCineInputPort existeCineInputPort;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public CineAdaptadorKafka(ExisteCineInputPort existeCineInputPort,
                            KafkaTemplate<String, String> kafkaTemplate,
                            ObjectMapper objectMapper) {
        this.existeCineInputPort = existeCineInputPort;
        this.kafkaTemplate = kafkaTemplate;
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
}
