package com.example.Cines.BloqueoAnuncio.Infraestructura.Kafka;


import com.example.Cines.BloqueoAnuncio.Aplicacion.CasosUso.CrearBloqueoAnuncios.CrearBloqueoAnuncioDTO;
import com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Input.CrearBloqueoAnunciosCineInputPort;
import com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Output.CalculoDiasSolapados;
import com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Output.DeterminarDiasSolapadosBloqueoOutputport;
import com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Output.ElimiinarBLoqueoCineEspecificoOutputPort;
import com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Output.ExisteBloqueoAnuncioCIne;
import com.example.Cines.BloqueoAnuncio.Dominio.BloqueoAnuncio;
import com.example.Cines.Cine.Aplicacion.Ports.Input.CambioMonetarioInputPort;
import com.example.Cines.Cine.Infraestructura.Kafka.DTO.VerficarCIne.VerificarCineDTO;
import com.example.Cines.Cine.Infraestructura.Kafka.DTO.VerficarCIne.VerificarCineRespuestaDTO;
import com.example.comun.DTO.BloqueoAnuncios.BloqueoCineDTO;
import com.example.comun.DTO.BloqueoAnuncios.CreditoUsuarioBloqueo;
import com.example.comun.DTO.BloqueoAnuncios.CreditoUsuarioBloqueoEspecifico;
import com.example.comun.DTO.BloqueoAnuncios.PeticionBloqueoAnuncio;
import com.example.comun.DTO.DW.ListadoFacturadoAnuncio;
import com.example.comun.DTO.DW.ReplicacionFacturaAnuncioDTO;
import com.example.comun.DTO.FacturaAnuncio.DiasDescuentoAnunciosBloqueados;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class BloqueoAnuncioKafkaAdaptador {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final DeterminarDiasSolapadosBloqueoOutputport calculoDiasSolapados;
    private final CambioMonetarioInputPort cambioMonetarioInputPort;
    private final CrearBloqueoAnunciosCineInputPort crearBloqueoAnunciosCineInputPort;

    private final ElimiinarBLoqueoCineEspecificoOutputPort elimiinarBLoqueoCineEspecificoOutputPort;

//

    @KafkaListener(topics = "generar-bloqueo-anuncio", groupId = "cines-group")
    public void verificarCine(@Payload String mensaje, @Header(KafkaHeaders.CORRELATION_ID) String correlationId) throws Exception {
        //Para el DW
        List<ReplicacionFacturaAnuncioDTO> listadoReplicacionDW  = new ArrayList<>();

        // Deserializar el mensaje
        BloqueoCineDTO solicitud = objectMapper.readValue(mensaje, BloqueoCineDTO.class);
        //guarddar
        System.out.println(solicitud.getCine()+" llega   "+solicitud.getFecha());
        // descuenta a los cines
        BloqueoAnuncio cine = this.crearBloqueoAnunciosCineInputPort.CrearBloqueoAnuncio(new CrearBloqueoAnuncioDTO( solicitud.getFecha(), solicitud.getCantidad_dias(),solicitud.getCine()));
        List<DiasDescuentoAnunciosBloqueados> anunciosBloqueados = new ArrayList<>();
        Double costoFinal =0.0;

        //listado para enviaer a los usuarios
        List<CreditoUsuarioBloqueoEspecifico> liotadoCredito = new ArrayList<>();
        System.out.println("aca"+ cine.getIdCine().getId()+ "-- " +cine.getIdCine().getNombre());

        // ver si se puede debuitar
        try{
            for (PeticionBloqueoAnuncio bloque : solicitud.getPeticiones()) {
                System.out.println("antes del calculo");
                System.out.println(bloque.getFechainicio()+ "--- "+ bloque.getFechafin());
                System.out.println(cine.getFecha()+ "--- "+ cine.getFecha_fin());
                Object dias = this.calculoDiasSolapados.diasSolapadosBloqueo(cine.getIdCine().getId(), bloque.getFechainicio(), bloque.getFechafin(), cine.getFecha(), cine.getFecha_fin());
                System.out.println("error aca" + (dias instanceof Object[]));
                if (dias instanceof Object[]) {
                    Object[] fila = (Object[]) dias;
                    Long diasSolapados = ((Number) fila[0]).longValue();
                    Long diasRango = ((Number) fila[1]).longValue();
                    Long diasCubiertos = ((Number) fila[2]).longValue();
                    Double precio = (Double) fila[3];


                    DiasDescuentoAnunciosBloqueados cineEspecifico = new DiasDescuentoAnunciosBloqueados();
                    cineEspecifico.setDiasSolapados(diasSolapados);
                    cineEspecifico.setDiasRango(diasRango);
                    cineEspecifico.setDiasCubiertos(diasCubiertos);
                    cineEspecifico.setPrecio(precio);
                    cineEspecifico.setCine(cine.getIdCine().getId());

                    cineEspecifico.setEstado(false);
                    Double precioEspecifico = precio * diasSolapados;
                    costoFinal += precioEspecifico;

                    // devolucion especifica
                    CreditoUsuarioBloqueoEspecifico nuevoCredito = new CreditoUsuarioBloqueoEspecifico();
                    nuevoCredito.setUserId(bloque.getUsuario());
                    nuevoCredito.setMonto(precioEspecifico);
                    nuevoCredito.setCorrelationId(correlationId);

                    System.out.println("precioEspecifico: " + precioEspecifico+ " dias "+ diasSolapados+ " rango "+ diasRango+ "dias cubiertos "+diasCubiertos);


                    liotadoCredito.add(nuevoCredito);

                    //aca genera los valores
                    ReplicacionFacturaAnuncioDTO replicacionFacturaAnuncioDTO  = new ReplicacionFacturaAnuncioDTO();
                    replicacionFacturaAnuncioDTO.setCine(cine.getIdCine().getId());
                    replicacionFacturaAnuncioDTO.setAnuncio(bloque.getAnuncio());
                    replicacionFacturaAnuncioDTO.setUsuario(bloque.getUsuario());
                    replicacionFacturaAnuncioDTO.setMonto(precioEspecifico);
                    replicacionFacturaAnuncioDTO.setFecha(cine.getFecha());
                    replicacionFacturaAnuncioDTO.setId(UUID.randomUUID());
                    replicacionFacturaAnuncioDTO.setDetalle("Pago de ocultacion de anuncio");
                    replicacionFacturaAnuncioDTO.setEstado("PENDIENTE");

                    listadoReplicacionDW.add(replicacionFacturaAnuncioDTO);


                    anunciosBloqueados.add(cineEspecifico);

                }
            }

            this.cambioMonetarioInputPort.cambioMonetario(cine.getIdCine().getId(), -costoFinal, true);

        }catch (Exception e) {
            // aca elimnar el bloqueo ingresado
            this.elimiinarBLoqueoCineEspecificoOutputPort.elimiinarBLoqueoCineEspecificoOutputPort(cine.getId());
            throw new RuntimeException("Fallo en debitar el evento de bloque de cine: " + e.getMessage(), e);

        }


        // si tod esta bien enviar al usuario y acreditarle a cada uno
        //generar respuesta
        CreditoUsuarioBloqueo respuesta = new CreditoUsuarioBloqueo();
        respuesta.setListado(liotadoCredito);
        respuesta.setCorrelationId(correlationId);
        String respuestaMensaje = objectMapper.writeValueAsString(respuesta);
        Message<String> kafkaMessage = MessageBuilder
                .withPayload(respuestaMensaje)
                .setHeader(KafkaHeaders.TOPIC, "acreditacion-usuario-bloqueo")
                .setHeader(KafkaHeaders.CORRELATION_ID, correlationId)
                .build();

        kafkaTemplate.send(kafkaMessage);

        // envio al dw

        ListadoFacturadoAnuncio nuevoValor = new ListadoFacturadoAnuncio();
        nuevoValor.setListado(listadoReplicacionDW);
        String respuestaDebito = objectMapper.writeValueAsString(nuevoValor);

        Message<String> kafkaMessageDebitoUsuario = MessageBuilder
                .withPayload(respuestaDebito)
                .setHeader(KafkaHeaders.TOPIC, "ingreso-detalle-anuncio-bloqueo")
                .setHeader(KafkaHeaders.CORRELATION_ID, correlationId)
                .build();
        kafkaTemplate.send(kafkaMessageDebitoUsuario);

    }
}

