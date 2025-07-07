package websocketgateway.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import websocketgateway.dto.ChatMessageResponse;

import java.security.Principal;
import java.time.Duration;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @EventListener
    public void handleSubscribeEvent(SessionSubscribeEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        Principal user = accessor.getUser();

        if (user != null) {
            String userId = user.getName();
            logger.info("📡 User [{}] subscribed to WebSocket", userId);

            // Mark user as online
           // redisTemplate.opsForValue().set("online:" + userId, "1");
            redisTemplate.opsForValue().set("online:" + userId, "1", Duration.ofMinutes(5));

            // Deliver offline messages
            String redisKey = "undelivered:" + userId;
            Long size = redisTemplate.opsForList().size(redisKey);

            if (size != null && size > 0) {
                logger.info("📦 Delivering {} offline message(s) to [{}]", size, userId);
                for (int i = 0; i < size; i++) {
                    Object message = redisTemplate.opsForList().leftPop(redisKey);
                    if (message instanceof ChatMessageResponse response) {
                        messagingTemplate.convertAndSendToUser(userId, "/queue/messages", response);
                        logger.info("📤 Delivered offline message to [{}]: {}", userId, response);
                    }
                }
            } else {
                logger.info("✅ No offline messages found for [{}]", userId);
            }
        }
    }
}
