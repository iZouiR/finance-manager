package org.izouir.budgetplanningservice.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.izouir.budgetplanningservice.dto.GoalNotificationDto;
import org.izouir.budgetplanningservice.dto.LimitationNotificationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public Map<String, Object> producerFactoryProperties() {
        final var properties = new HashMap<String, Object>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return properties;
    }

    @Bean
    public ProducerFactory<String, GoalNotificationDto> goalNotificationProducerFactory(final Map<String, Object> properties) {
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public ProducerFactory<String, LimitationNotificationDto> limitationNotificationProducerFactory(final Map<String, Object> properties) {
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String, GoalNotificationDto> goalNotificationKafkaTemplate() {
        return new KafkaTemplate<>(goalNotificationProducerFactory(producerFactoryProperties()));
    }

    @Bean
    public KafkaTemplate<String, LimitationNotificationDto> limitationNotificationKafkaTemplate() {
        return new KafkaTemplate<>(limitationNotificationProducerFactory(producerFactoryProperties()));
    }
}
