package com.example.Cines.Config;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Configuration
public class KafkaTopicConfig {


    @Value("${SPRING_KAFKA_BOOTSTRAP_SERVERS}")
    private String bootstrapServers;

    @Value("${MSK_TRUSTSTORE_PASSWORD}")
    private String truststorePassword;

    private static final List<NewTopic> TOPICS = List.of(
            new NewTopic("propiedad-anuncio-creado", 3, (short) 2),
            new NewTopic("verificar-cine", 3, (short) 2)
    );

    @Bean
    public AdminClient adminClient() {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put("security.protocol", "SSL");
        props.put("ssl.truststore.location", "/etc/msk-truststore/truststore.jks");
        props.put("ssl.truststore.password", truststorePassword);
        return AdminClient.create(props);
    }

    @PostConstruct
    public void createTopics() {
        try (var client = adminClient()) {
            var result = client.createTopics(TOPICS);
            result.all().get(30, TimeUnit.SECONDS);
            System.out.println("Tópicos Kafka creados: " + TOPICS.stream().map(NewTopic::name).toList());
        } catch (Exception e) {
            if (!e.getMessage().contains("Topic 'propiedad-anuncio-creado' already exists.") &&
                    !e.getMessage().contains("Topic 'verificar-cine' already exists.")) {
                System.err.println("Error creando tópicos: " + e.getMessage());
            } else {
                System.out.println("Tópicos ya existen. Continuando...");
            }
        }
    }
}



