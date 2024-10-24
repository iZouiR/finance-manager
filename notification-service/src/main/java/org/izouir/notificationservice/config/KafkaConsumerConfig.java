package org.izouir.notificationservice.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.izouir.notificationservice.dto.GoalNotificationDto;
import org.izouir.notificationservice.dto.LimitationNotificationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    public Map<String, Object> consumerFactoryProperties() {
        final var properties = new HashMap<String, Object>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return properties;
    }

    @Bean
    public ConsumerFactory<String, GoalNotificationDto> goalConsumerFactory(final Map<String, Object> properties) {
        final var typeMapper = new DefaultJackson2JavaTypeMapper();
        final var classMap = new HashMap<String, Class<?>>();
        classMap.put("org.izouir.notificationservice.dto.GoalNotificationDto", GoalNotificationDto.class);
        typeMapper.setIdClassMapping(classMap);
        typeMapper.addTrustedPackages("*");
        final var jsonDeserializer = new JsonDeserializer<>(GoalNotificationDto.class);
        jsonDeserializer.setTypeMapper(typeMapper);

        return new DefaultKafkaConsumerFactory<>(properties,
                new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConsumerFactory<String, LimitationNotificationDto> limitationConsumerFactory(final Map<String, Object> properties) {
        final var typeMapper = new DefaultJackson2JavaTypeMapper();
        final var classMap = new HashMap<String, Class<?>>();
        classMap.put("org.izouir.notificationservice.dto.LimitationNotificationDto", LimitationNotificationDto.class);
        typeMapper.setIdClassMapping(classMap);
        typeMapper.addTrustedPackages("*");
        final var jsonDeserializer = new JsonDeserializer<>(LimitationNotificationDto.class);
        jsonDeserializer.setTypeMapper(typeMapper);

        return new DefaultKafkaConsumerFactory<>(properties,
                new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, GoalNotificationDto> goalListener() {
        final var factory = new ConcurrentKafkaListenerContainerFactory<String, GoalNotificationDto>();
        factory.setConsumerFactory(goalConsumerFactory(consumerFactoryProperties()));
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, LimitationNotificationDto> limitationListener() {
        final var factory = new ConcurrentKafkaListenerContainerFactory<String, LimitationNotificationDto>();
        factory.setConsumerFactory(limitationConsumerFactory(consumerFactoryProperties()));
        return factory;
    }
}
