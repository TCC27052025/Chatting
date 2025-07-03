package websocketgateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import websocketgateway.dto.ChatMessageRequest;
import websocketgateway.dto.ChatMessageResponse;
import websocketgateway.kafka.MessageProducer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * Service for handling WebSocket chat messages.
 * This service processes incoming chat messages and seen events,
 * interacts with Kafka for message production, and sends responses
 * to the appropriate WebSocket queues.
 */


@Service
public class WebSocketService {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketService.class);

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * Process a new message sent by a user.
     */
    public ChatMessageResponse processSendMessage(ChatMessageRequest request) {
        logger.info("📨 Processing message from sender [{}] to receiver [{}]",
                request.getSenderId(), request.getReceiverId());

        // Send the message to Kafka
        messageProducer.send("chat.message.sent", request);
        logger.info("🟡 Message sent to Kafka topic [chat.message.sent]: {}", request);

        // Prepare response
        ChatMessageResponse response = new ChatMessageResponse(
                request.getChatId(),
                request.getSenderId(),
                request.getReceiverId(),
                request.getContent(),
                request.getMessageType(),
                "SENT",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );

        logger.info("Response Details: chatId={}, senderId={}, receiverId={}, content={}, messageType={}, status={}, timestamp={}",
        response.getChatId(),
        response.getSenderId(),
        response.getReceiverId(),
        response.getContent(),
        response.getMessageType(),
        response.getStatus());


        // Check if receiver is online using Redis
        String redisKey = "online:" + request.getReceiverId();
        Object onlineStatus = redisTemplate.opsForValue().get(redisKey);

        if ("1".equals(onlineStatus)) {
            // Deliver message instantly
            messagingTemplate.convertAndSendToUser(
                    String.valueOf(request.getReceiverId()),
                    "/queue/messages",
                    response
                    //the repsonse object is sent to the reciver's private queue
            );
            response.setStatus("DELIVERED");
            logger.info("📤 Message delivered to user [{}] on /queue/messages", request.getReceiverId());
        } else {
            // Store message in Redis list for offline delivery
            redisTemplate.opsForList().rightPush("undelivered:" + request.getReceiverId(), response);
            logger.info("💤 User [{}] is offline. Message stored in Redis.", request.getReceiverId());
        }

        // Notify sender
        messagingTemplate.convertAndSendToUser(
                String.valueOf(request.getSenderId()),
                "/queue/status",
                response
        );
        logger.info("✅ Delivery status sent to sender [{}] on /queue/status", request.getSenderId());

        return response;
    }

    /**
     * Process a 'message seen' event.
     *
     * @return
     */
    public ChatMessageResponse processSeenEvent(ChatMessageRequest request) {
        logger.info("👁️ Processing 'message seen' event for chatId [{}] by receiver [{}]",
                request.getChatId(), request.getReceiverId());

        // Send event to Kafka
        messageProducer.send("chat.message.seen", request);
        logger.info("📬 'Message seen' event sent to Kafka topic [chat.message.seen]");
        //Prepare response for seen event

        // Notify sender
        ChatMessageResponse response = new ChatMessageResponse(
                request.getChatId(),
                request.getSenderId(),
                request.getReceiverId(),
                request.getContent(),
                request.getMessageType(),
                "SEEN",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );

        messagingTemplate.convertAndSendToUser(
                String.valueOf(request.getSenderId()),
                "/queue/status",
                response
        );
        logger.info("✅ Seen status sent to sender [{}]", request.getSenderId());
        return response;
    }

    /**
     * Deliver any offline messages stored in Redis when user comes online.
     */
    public void deliverOfflineMessagesIfAny(Long userId) {
        String redisKey = "undelivered:" + userId;
        List<Object> pending = redisTemplate.opsForList().range(redisKey, 0, -1);

        if (pending != null && !pending.isEmpty()) {
            logger.info("📦 Found {} offline messages for user [{}]", pending.size(), userId);
            for (Object obj : pending) {
                if (obj instanceof ChatMessageResponse) {
                    ChatMessageResponse msg = (ChatMessageResponse) obj;
                    msg.setStatus("DELIVERED");

                    // Deliver to receiver
                    messagingTemplate.convertAndSendToUser(
                            String.valueOf(userId),
                            "/queue/messages",
                            msg
                    );

                    // ✅ Also notify sender
                    messagingTemplate.convertAndSendToUser(
                            String.valueOf(msg.getSenderId()),
                            "/queue/status",
                            msg
                    );
                }
            }
            redisTemplate.delete(redisKey);
            logger.info("🧹 Offline queue for user [{}] cleared after delivery", userId);
        }
    }
}