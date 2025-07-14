package in.ch.message_service.config;

import in.ch.message_service.dto.MessageSentEventDto;
import in.ch.message_service.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
    private final MessageService messageService;

    public MessageConsumer(MessageService messageService) {
        this.messageService = messageService;
    }

    @KafkaListener(
            topics = "chat.message.sent",
            groupId = "chat-group",
            containerFactory = "kafkaListenerContainerFactoryWithDLT"
    )
    public void consumeMessage(MessageSentEventDto message) {
        logger.info("✅ Consumed from Kafka: {}", message);
        saveMessage(message);
    }

    private void saveMessage(MessageSentEventDto message) {
        try {
            messageService.saveMessage(message);
            logger.info("✅ Message saved successfully: {}", message);
        } catch (Exception e) {
            logger.error("❌ Failed to save message: {}", message, e);
        }
    }
}

