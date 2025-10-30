package com.example.Cines.Snacks.Infraestructura.Eventos;

import com.example.Cines.Snacks.Aplicacion.Ports.Input.ListarSnacksEspecificasInputPort;
import com.example.Cines.Snacks.Dominio.Snacks;
import com.example.comun.DTO.PeticionSnackEspecifica.SnackDTO;
import com.example.comun.DTO.PeticionSnackEspecifica.VerificarSnackDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SnacksKafkaAdaptador {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final ListarSnacksEspecificasInputPort listarSnacksEspecificasInputPort;

    @KafkaListener(topics = "verificar-snack", groupId = "cine-group")
    public void handleVerificarSnack(
            @Payload String mensaje,
            @Header(KafkaHeaders.CORRELATION_ID) String correlationId) {

        try {
            VerificarSnackDTO verificar = objectMapper.readValue(mensaje, VerificarSnackDTO.class);
            System.out.println("verificar snack received"+verificar.getSnackId());
            Snacks snack = listarSnacksEspecificasInputPort.ListarSnacksEspecificas(verificar.getSnackId());

            System.out.println("aca encontro"+snack.getNombre());
            SnackDTO respuesta = new SnackDTO();
            respuesta.setCorrelationId(correlationId);

            if (snack != null) {
                respuesta.setId(snack.getId());
                respuesta.setNombre(snack.getNombre());
                respuesta.setPrecio(snack.getPrecio());
                respuesta.setExiste(true);
            } else {
                respuesta.setExiste(false);
            }
            String jsonRespuesta = objectMapper.writeValueAsString(respuesta);

            Message<String> kafkaMessage = MessageBuilder
                    .withPayload(jsonRespuesta)
                    .setHeader(KafkaHeaders.TOPIC, "respuesta-verificar-snack")
                    .setHeader(KafkaHeaders.CORRELATION_ID, correlationId)
                    .build();
            kafkaTemplate.send(kafkaMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
