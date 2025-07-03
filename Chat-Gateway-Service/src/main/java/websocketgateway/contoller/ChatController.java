package websocketgateway.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import websocketgateway.dto.ChatMessageRequest;
import websocketgateway.dto.ChatMessageResponse;
import websocketgateway.security.JwtUtil;
import websocketgateway.service.WebSocketService;

import java.security.Principal;

/**
 * Controller for handling chat messages.
 * This controller processes incoming chat messages and seen events.
 */



@Controller
public class ChatController {

    @Autowired
    private WebSocketService chatService;


    @MessageMapping("/chat.sendMessage")
    public ChatMessageResponse sendMessage(@Payload ChatMessageRequest message, Principal principal) {
        if (principal == null || principal.getName() == null) {
            System.out.println("🚨 Principal is NULL!");
            throw new IllegalArgumentException("Sender information is missing from Principal");
        }

        System.out.println("✅ Principal from WebSocket: " + principal.getName());

        Long senderId = Long.valueOf(principal.getName());
        message.setSenderId(senderId);

        return chatService.processSendMessage(message);
    }

    @MessageMapping("/chat.messageSeenBackend")
    public ChatMessageResponse messageSeen(@Payload ChatMessageRequest message, Principal principal) {
        if (principal == null || principal.getName() == null) {
            throw new IllegalArgumentException("Receiver information is missing from Principal");
        }

        // ✅ Use Principal as the one who saw the message (the receiver)
        Long receiverId = Long.valueOf(principal.getName());
        message.setReceiverId(receiverId);

        return chatService.processSeenEvent(message);
    }
}

