package in.ch.message_service.config;

import in.ch.message_service.dto.MessageSentEventDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    // ✅ Common producer properties
    private Map<String, Object> commonProducerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, true);
        return props;
    }

    // ✅ KafkaTemplate for each type
    @Bean
    public KafkaTemplate<String, MessageSentEventDto> messageDeliverKafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(commonProducerProps()));
    }

    @Bean
    public KafkaTemplate<String, MessageSentEventDto> messageSeenKafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(commonProducerProps()));
    }

    @Bean
    public KafkaTemplate<Object, Object> objectKafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(commonProducerProps()));
    }

    @Bean
    public KafkaTemplate<String, MessageSentEventDto> messageSentKafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(commonProducerProps()));
    }

    // ✅ Consumer Factory for MessageSentEventDto
    @Bean
    public ConsumerFactory<String, MessageSentEventDto> messageSentConsumerFactory() {
        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "chat-group");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);

        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class.getName());
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());

        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, MessageSentEventDto.class.getName());
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "in.ch.message_service.dto");
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);

        return new DefaultKafkaConsumerFactory<>(props);
    }

    // ✅ Error handler for dead-letter logic
    @Bean
    public DefaultErrorHandler errorHandler() {
        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(objectKafkaTemplate());
        DefaultErrorHandler handler = new DefaultErrorHandler(recoverer);
        handler.setRetryListeners((record, ex, attempt) ->
                System.out.println("❌ Retry failed (attempt " + attempt + ") for record: " + record.value()));
        return handler;
    }

    // ✅ Named container factory bean (fixes your error)
    @Bean(name = "kafkaListenerContainerFactoryWithDLT")
    public ConcurrentKafkaListenerContainerFactory<String, MessageSentEventDto> kafkaListenerContainerFactoryWithDLT() {
        ConcurrentKafkaListenerContainerFactory<String, MessageSentEventDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(messageSentConsumerFactory());
        factory.setCommonErrorHandler(errorHandler());
        return factory;
    }
}
