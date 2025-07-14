package websocketgateway.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import websocketgateway.dto.ChatMessageRequest;
import websocketgateway.dto.ChatMessageResponse;

import java.time.LocalDateTime;

@Service
public class MessageConsumer {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = "chat.message.sent", groupId = "chat-group")
    public void handleChatMessage(ChatMessageRequest request) {
        ChatMessageResponse response = new ChatMessageResponse();
        response.setChatId(request.getChatId());
        response.setSenderId(request.getSenderId());
        response.setReceiverId(request.getReceiverId());
        response.setContent(request.getContent());
        response.setMessageType(request.getMessageType());
        response.setStatus("DELIVERED");
        response.setSeenAt(LocalDateTime.now().toString());

        // ✅ Log for debugging
        System.out.println("📩 Sending message to receiverId: " + request.getReceiverId());

        // Send to user queue
     messagingTemplate.convertAndSendToUser(
        String.valueOf(request.getReceiverId()), "/queue/messages", response);


    }
}


