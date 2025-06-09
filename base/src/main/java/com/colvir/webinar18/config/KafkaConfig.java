package com.colvir.webinar18.config;

import com.colvir.webinar18.dto.EventDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.kafka.retrytopic.RetryTopicConfiguration;
import org.springframework.kafka.retrytopic.RetryTopicConfigurationBuilder;
import org.springframework.util.backoff.FixedBackOff;

import java.util.List;
import java.util.Map;

@Configuration
public class KafkaConfig {

    public static final String TOPIC_NAME = "colvir.events";

//    @Bean
//    public ProducerFactory<String, String> producerConfig() {
//        Map<String, Object> bootstrapServersConfig = Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
//                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
//                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        return new DefaultKafkaProducerFactory<>(bootstrapServersConfig);
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        return new KafkaTemplate<>(producerConfig());
//    }
//
//    @Bean
//    public ConsumerFactory<String, String> consumerFactory() {
//        Map<String, Object> bootstrapServersConfig = Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
//                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
//                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
//                ConsumerConfig.GROUP_ID_CONFIG, "EventConsumer");
//        return new DefaultKafkaConsumerFactory<>(bootstrapServersConfig);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        DefaultErrorHandler commonErrorHandler = new DefaultErrorHandler((consumerRecord, e) -> {
//
//        }, new FixedBackOff(1000L, 3));
//        factory.setCommonErrorHandler(commonErrorHandler);
//        return factory;
//    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(TOPIC_NAME).partitions(2).build();
    }

    @Bean
    public RetryTopicConfiguration retryTopicCreation(KafkaTemplate<String, EventDto> template) {
        return RetryTopicConfigurationBuilder.newInstance()
                .exponentialBackoff(1000, 2, 5000)
                .maxAttempts(3)
                .excludeTopics(List.of("test"))
                .dltHandlerMethod("customDltProcessor", "processDltMessage")
                .dltProcessingFailureStrategy(DltStrategy.FAIL_ON_ERROR)
                .notRetryOn(
                        List.of(
                                NullPointerException.class,
                                SerializationException.class))
                .create(template);
    }
}
