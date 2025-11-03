package com.example.Cines.Cine.Infraestructura.Kafka;

import com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Output.CalculoDiasSolapados;
import com.example.Cines.BloqueoAnuncio.Aplicacion.ports.Output.CinesAnuncioBloqueadoFechaOutputPort;
import com.example.Cines.BloqueoAnuncio.Dominio.BloqueoAnuncio;
import com.example.Cines.Cine.Aplicacion.Ports.Input.CambioMonetarioInputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Input.ExisteCineInputPort;
import com.example.Cines.Cine.Aplicacion.Ports.Input.ListarCinesQueNoEstanBloqueadosInputPort;
import com.example.Cines.Cine.Dominio.Cine;
import com.example.Cines.Cine.Infraestructura.Kafka.DTO.EstadoMonetarioAnuncioComprado.AnuncioFallidoDTO;
import com.example.Cines.Cine.Infraestructura.Kafka.DTO.EstadoMonetarioAnuncioComprado.CineActualizadoDTO;
import com.example.Cines.Cine.Infraestructura.Kafka.DTO.VerficarCIne.VerificarCineDTO;
import com.example.Cines.Cine.Infraestructura.Kafka.DTO.VerficarCIne.VerificarCineRespuestaDTO;
import com.example.comun.DTO.FacturaAnuncio.AnuncioCreadoDTO;
import com.example.comun.DTO.FacturaAnuncio.DebitoUsuarioAnuncio;
import com.example.comun.DTO.FacturaAnuncio.DiasDescuentoAnunciosBloqueados;
import com.example.comun.DTO.FacturaBoleto.CobroCineDTO;
import com.example.comun.DTO.FacturaBoleto.DebitoCine.DebitoCineDTO;
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

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CineAdaptadorKafka {
    private final ExisteCineInputPort existeCineInputPort;
    private final CambioMonetarioInputPort cambioMonetarioInputPort;
    //aca preguntar los cines con bloqueo
    private final ListarCinesQueNoEstanBloqueadosInputPort listarCinesQueNoEstanBloqueadosInputPort;
    private final CinesAnuncioBloqueadoFechaOutputPort cinesAnuncioBloqueadoFechaOutputPort;
    private final CalculoDiasSolapados calculoDiasSolapados;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public CineAdaptadorKafka(ExisteCineInputPort existeCineInputPort,
                              CambioMonetarioInputPort cambioMonetarioInputPort,
                            KafkaTemplate<String, String> kafkaTemplate,
                            ObjectMapper objectMapper,
                              ListarCinesQueNoEstanBloqueadosInputPort listarCinesQueNoEstanBloqueadosInputPort,
                              CinesAnuncioBloqueadoFechaOutputPort cinesAnuncioBloqueadoFechaOutputPort,
                              CalculoDiasSolapados calculoDiasSolapados) {
        this.existeCineInputPort = existeCineInputPort;
        this.kafkaTemplate = kafkaTemplate;
        this.cambioMonetarioInputPort=cambioMonetarioInputPort;
        this.objectMapper = objectMapper;
        this.listarCinesQueNoEstanBloqueadosInputPort=listarCinesQueNoEstanBloqueadosInputPort;
        this.cinesAnuncioBloqueadoFechaOutputPort=cinesAnuncioBloqueadoFechaOutputPort;
        this.calculoDiasSolapados=calculoDiasSolapados;
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
        List<DiasDescuentoAnunciosBloqueados> idCine = new ArrayList<>();
        List<DiasDescuentoAnunciosBloqueados> idCineBloqueado = new ArrayList<>();
        Integer cantidadCines =0;
        String motivoFallo = "Error desconocido";
        System.out.println("dinero: "+evento.getCosto()+" id: "+ evento.getFactura());

        System.out.println(evento.getFechafin()+ " "+ evento.getFechainicio());
        // Validate DTO

        //genera costo globla
            Double costoAnuncio = ChronoUnit.DAYS.between(
                    evento.getFechainicio(), evento.getFechafin()
            )*
                    evento.getCosto();


            //busca cines
        List<Cine> cines = this.listarCinesQueNoEstanBloqueadosInputPort.listaCinesAnunciosNoBloqueados(evento.getFechainicio(), evento.getFechafin());

        //ciclo para pagar
        try{
            for (Cine cine : cines) {
                System.out.println("aca se hizo el pago"+costoAnuncio);
                System.out.println(cine.getId());
                cambioMonetarioInputPort.cambioMonetario(cine.getId(), costoAnuncio, true);

                DiasDescuentoAnunciosBloqueados cineEspecifico = new DiasDescuentoAnunciosBloqueados();

                cineEspecifico.setPrecio(costoAnuncio);
                cineEspecifico.setCine(cine.getId());
                cineEspecifico.setEstado(true);

                idCine.add(cineEspecifico);
                cantidadCines++;
            }
            exito = true;
            //actualiza costo final al usuario
            costoAnuncio = cantidadCines*costoAnuncio;
        }catch (Exception e){
            exito=false;
            motivoFallo = "NO se pudo actualizar el cine";
            throw new Exception(motivoFallo);
        }
        System.out.println(costoAnuncio);

        // aca debera de detarminar los cines que tienen bloqueos y descontar
        //calculod  de dias
        List<BloqueoAnuncio> bloqueoAnuncios = this.cinesAnuncioBloqueadoFechaOutputPort.listaCinesAnunciosBloqueados(evento.getFechainicio(), evento.getFechafin());
        for (BloqueoAnuncio bloque : bloqueoAnuncios) {
            Object dias = this.calculoDiasSolapados.diasSolapados(bloque.getIdCine().getId(), evento.getFechainicio(), evento.getFechafin());
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
                cineEspecifico.setCine(bloque.getIdCine().getId());
                cineEspecifico.setEstado(false);

                 idCineBloqueado.add(cineEspecifico);
                idCine.add(cineEspecifico);

            }
        }

        // descuenta a los cines
       try{
           for (DiasDescuentoAnunciosBloqueados cine : idCineBloqueado) {
               double costoFinal = cine.getPrecio()*cine.getDiasSolapados();
               cambioMonetarioInputPort.cambioMonetario(cine.getCine(), -costoFinal, true);
             //  idCine.add(cine.getId());
              // cantidadCines++;
               costoAnuncio-=costoFinal;

               cine.setPrecio(costoFinal);
           }
           exito = true;
       } catch (Exception e){
           motivoFallo = "NO se pudo actualizar el cine";
           throw new Exception(motivoFallo);
       }

       // une ambos



        // Publica
        if (exito) {
            System.out.println(costoAnuncio);
            DebitoUsuarioAnuncio respuesta = new DebitoUsuarioAnuncio();
            respuesta.setIdAnuncio(evento.getAnuncioId());
            respuesta.setCorrelationId(correlationId);
            respuesta.setMonto(costoAnuncio);
            respuesta.setMotivo("Pago a los cines");
            respuesta.setFactura(evento.getFactura());
            respuesta.setDineroCines(idCine);

            //enviarle el listado de cimes para pagar y los bloqueados

            respuesta.setUserId(evento.getUsuarioId());
            String respuestaMensaje = objectMapper.writeValueAsString(respuesta);
            Message<String> kafkaMessage = MessageBuilder
                    .withPayload(respuestaMensaje)
                    .setHeader(KafkaHeaders.TOPIC, "acreditacion-usuario-anuncio-facturacion")
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
        respuesta.setFactura(evento.getFactura());

//        // para ventas
//        String json = objectMapper.writeValueAsString(respuesta);
//        String topic = exito ? "venta-actualizada" : "venta-fallido";
//        // para facturas
//        String topicFactura = exito ? "factura-actualizada" : "factura-fallido";
//
//
//        kafkaTemplate.send(topic, json);
//        kafkaTemplate.send(topicFactura, json);

    }

    public RespuestaFacturaBoletoCreadoDTO respuestaGenerada(UUID ventaId, boolean exito, String motivoFallo, String correlationId) {
        RespuestaFacturaBoletoCreadoDTO nuevaRespuesta = new RespuestaFacturaBoletoCreadoDTO();
        nuevaRespuesta.setMotivoFallo(motivoFallo);
        nuevaRespuesta.setCorrelationId(correlationId);
        nuevaRespuesta.setExito(exito);
        nuevaRespuesta.setVentaId(ventaId);
        return nuevaRespuesta;
    }

    //substraer para el cine en caso no este bien la transaccion
    @KafkaListener(topics = "debito-cine", groupId = "cines-group")
    @Transactional
    public void debitarCine(@Payload String mensaje, @Header(KafkaHeaders.CORRELATION_ID) String correlationId) throws Exception {
        DebitoCineDTO evento = objectMapper.readValue(mensaje, DebitoCineDTO.class);
        String motivoFallo = "Error desconocido";
        boolean exito = false;
        if (evento.getIdCine() == null || evento.getMonto() <= 0) {
            motivoFallo = "Costo o cine invalido: " + evento;
        } else if (!existeCineInputPort.existeCineEspecifico(evento.getIdCine())) {
            motivoFallo = "Cine no existe: " + evento.getIdCine();
        } else {
            System.out.println("dinero acaaaaaaaaaaaaaaaaaa: "+evento.getMonto());
            System.out.println("debitaaaaaaaaaa");
            exito = cambioMonetarioInputPort.cambioMonetario(evento.getIdCine(), -evento.getMonto(), true);
            if (!exito) {
                motivoFallo = "NO se pudo actualizar";
            }
        }



    }

    @KafkaListener(topics = "credito-cine", groupId = "cines-group")
    @Transactional
    public void acreditarCine(@Payload String mensaje, @Header(KafkaHeaders.CORRELATION_ID) String correlationId) throws Exception {
        DebitoCineDTO evento = objectMapper.readValue(mensaje, DebitoCineDTO.class);
        String motivoFallo = "Error desconocido";
        boolean exito = false;
        if (evento.getIdCine() == null || evento.getMonto() <= 0) {
            motivoFallo = "Costo o cine invalido: " + evento;
        } else if (!existeCineInputPort.existeCineEspecifico(evento.getIdCine())) {
            motivoFallo = "Cine no existe: " + evento.getIdCine();
        } else {
            System.out.println("dinero acaaaaaaaaaaaaaaaaaa: "+evento.getMonto());
            System.out.println("debitaaaaaaaaaa");
            exito = cambioMonetarioInputPort.cambioMonetario(evento.getIdCine(), evento.getMonto(), true);
            if (!exito) {
                motivoFallo = "NO se pudo actualizar";
            }
        }



    }


}
