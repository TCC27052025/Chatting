package in.ch.message_service.service;

import in.ch.message_service.dto.MessageSentEventDto;
import in.ch.message_service.entity.*;
import in.ch.message_service.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageService {

    private final MessageIdRepository messageIdRepository;
    private final MessageSenderRepository messageSenderRepository;
    private final MessageReceiverRepository messageReceiverRepository;
    private final MessageChatRepository messageChatRepository;

    private MessageStatusRepository messageStatusRepository;
    private MessageContentRepository messageContentRepository;
    private MessageTypeRepository messageTypeRepository;

    @PersistenceContext
    private EntityManager entityManager;


    public MessageService(
            MessageIdRepository messageIdRepository, MessageSenderRepository messageSenderRepository, MessageReceiverRepository messageReceiverRepository, MessageChatRepository messageChatRepository, MessageStatusRepository messageStatusRepository, MessageContentRepository messageContentRepository, MessageTypeRepository messageTypeRepository, SentAtRepository sentAtRepository) {
        this.messageIdRepository = messageIdRepository;
        this.messageSenderRepository = messageSenderRepository;
        this.messageReceiverRepository = messageReceiverRepository;
        this.messageChatRepository = messageChatRepository;
        this.messageStatusRepository = messageStatusRepository;
        this.messageContentRepository = messageContentRepository;
        this.messageTypeRepository = messageTypeRepository;
        this.sentAtRepository = sentAtRepository;
    }

    private SentAtRepository sentAtRepository;


    @Transactional
    public void saveMessage(MessageSentEventDto response) {
        try {

            // Generate unique message ID

            String messageId = generateUniqueMessageId();
            Long senderId = response.getSenderId();
            String receiverId = response.getReceiverId().toString();
            String chatId = response.getChatId();
            String content = response.getContent();
            String messageType = response.getMessageType().toString();


            // Always create new instance
            MessageId messageIdEntity = new MessageId(messageId);

            // Use entityManager.merge() to avoid session conflict
            messageIdEntity = entityManager.merge(messageIdEntity); // <-- important

            messageSenderRepository.saveAndFlush(new MessageSender(messageIdEntity, senderId.toString()));
            messageReceiverRepository.saveAndFlush(new MessageReceiver(messageIdEntity, receiverId));



            ChatId chat = new ChatId(chatId);
            chat.setMessage(messageIdEntity);
            messageChatRepository.saveAndFlush(chat);

            messageContentRepository.saveAndFlush(new MessageContent(messageIdEntity, content));
            messageTypeRepository.saveAndFlush(new MessageType(messageIdEntity, messageType));

        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to save message: " + e.getMessage(), e);
        }
    }

    /**
     * Generates a unique message ID using UUID.
     *
     * @return A unique message ID as a string.
     */

    public static String generateUniqueMessageId() {
        // Generate a unique ID using UUID and convert it to a string
        return UUID.randomUUID().toString();
    }

}

