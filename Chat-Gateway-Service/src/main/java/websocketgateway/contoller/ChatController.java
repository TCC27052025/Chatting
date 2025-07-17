<<<<<<< HEAD
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


=======
//package websocketgateway.contoller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//import websocketgateway.dto.ChatMessageRequest;
//import websocketgateway.dto.ChatMessageResponse;
//import websocketgateway.dto.ChatRoomResponse;
//import websocketgateway.dto.CreateChatRoomRequest;
//import websocketgateway.service.ChatRoomClientService;
//import websocketgateway.service.WebSocketService;
//
//import java.security.Principal;
//import java.util.List;
//
//@Controller
//public class ChatController {
//
//    private static final Logger log = LoggerFactory.getLogger(ChatController.class);
//
//    // --- Constants for WebSocket Destinations ---
//    private static final String TOPIC_PREFIX = "/topic/chat/";
//    private static final String USER_QUEUE_ROOMS = "/queue/rooms";
//
//    private final WebSocketService chatService;
//    private final ChatRoomClientService chatRoomClientService;
//    private final SimpMessagingTemplate messagingTemplate;
//
//    @Autowired
//    public ChatController(WebSocketService chatService, ChatRoomClientService chatRoomClientService, SimpMessagingTemplate messagingTemplate) {
//        this.chatService = chatService;
//        this.chatRoomClientService = chatRoomClientService;
//        this.messagingTemplate = messagingTemplate;
//    }
//
//    @MessageMapping("/chat.sendMessage")
//    public void sendMessage(@Payload final ChatMessageRequest message, final Principal principal) {
//        if (principal == null || principal.getName() == null) {
//            log.error("Principal is NULL! Cannot process message without sender information.");
//            // In a real app, you might send an error message back to the user's private queue
//            throw new IllegalArgumentException("Sender information is missing from Principal");
//        }
//
//        final Long senderId = Long.valueOf(principal.getName());
//        message.setSenderId(senderId);
//
//        if (isNewChat(message)) {
//            handleNewChatRoomCreation(senderId, message);
//        } else {
//            processAndBroadcastMessage(message);
//        }
//    }
//
//    /**
//     * Handles the event when a user has seen a message.
//     * The @MessageMapping is corrected to match the client's destination.
//     */
//    @MessageMapping("/chat.seenMessage") // ✅ CORRECTED
//    public void messageSeen(@Payload final ChatMessageRequest message, final Principal principal) {
//        if (principal == null || principal.getName() == null) {
//            log.error("Principal is NULL! Cannot process seen event without receiver information.");
//            throw new IllegalArgumentException("Receiver information is missing from Principal");
//        }
//
//        final Long receiverId = Long.valueOf(principal.getName());
//        message.setReceiverId(receiverId);
//
//        ChatMessageResponse seenConfirmation = chatService.processSeenEvent(message);
//
//        // Send the "seen" confirmation to the specific chat topic
//        messagingTemplate.convertAndSend(TOPIC_PREFIX + seenConfirmation.getChatId(), seenConfirmation);
//    }
//
//    // --- Private Helper Methods for Clarity and Separation of Concerns ---
//
//    private boolean isNewChat(final ChatMessageRequest message) {
//        return message.getChatId() == null || message.getChatId().isBlank();
//    }
//
//    private void handleNewChatRoomCreation(final Long senderId, final ChatMessageRequest message) {
//        if (message.getReceiverId() == null) {
//            log.error("Error: receiverId is null for a new chat request from senderId: {}", senderId);
//            // It's crucial to stop execution here if the recipient is unknown.
//            return;
//        }
//
//        log.info("chatId is null. Triggering new room creation for participants: {}, {}", senderId, message.getReceiverId());
//
//        CreateChatRoomRequest createRequest = new CreateChatRoomRequest();
//        createRequest.setParticipantIds(List.of(senderId, message.getReceiverId()));
//
//        // Asynchronously call the Chat Room Service with proper error handling
//        chatRoomClientService.createChatRoom(createRequest)
//                .subscribe(
//                        // Success Consumer
//                        chatRoomResponse -> {
//                            log.info("New chat room created. New ChatId: {}", chatRoomResponse.getChatId());
//                            message.setChatId(chatRoomResponse.getChatId());
//                            processAndBroadcastMessage(message);
//                            notifyUsersOfNewRoom(senderId, message.getReceiverId(), chatRoomResponse);
//                        },
//                        // ✅ Error Consumer: Crucial for robust reactive code
//                        error -> {
//                            log.error("Failed to create new chat room for participants {} and {}.", senderId, message.getReceiverId(), error);
//                            // Optionally, notify the sender that their message could not be sent.
//                        }
//                );
//    }
//
//    private void processAndBroadcastMessage(final ChatMessageRequest message) {
//        ChatMessageResponse processedMessage = chatService.processSendMessage(message);
//        messagingTemplate.convertAndSend(TOPIC_PREFIX + processedMessage.getChatId(), processedMessage);
//    }
//
//    private void notifyUsersOfNewRoom(final Long senderId, final Long receiverId, final ChatRoomResponse chatRoomResponse) {
//        messagingTemplate.convertAndSendToUser(senderId.toString(), USER_QUEUE_ROOMS, chatRoomResponse);
//        messagingTemplate.convertAndSendToUser(receiverId.toString(), USER_QUEUE_ROOMS, chatRoomResponse);
//    }
//}

package websocketgateway.contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import websocketgateway.dto.ChatMessageRequest;
import websocketgateway.dto.ChatMessageResponse;
import websocketgateway.dto.ChatRoomResponse;
import websocketgateway.dto.CreateChatRoomRequest;
import websocketgateway.service.ChatRoomClientService;
import websocketgateway.service.WebSocketService;

import java.security.Principal;
import java.util.List;
>>>>>>> 6a1d08851ff8a3e2ea7a9353b54c701c7a204385

@Controller
public class ChatController {

<<<<<<< HEAD
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

=======
    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

    // --- Constants for WebSocket Destinations ---
    private static final String TOPIC_PREFIX = "/topic/chat/";
    private static final String USER_QUEUE_ROOMS = "/queue/rooms";

    private final WebSocketService chatService;
    private final ChatRoomClientService chatRoomClientService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ChatController(WebSocketService chatService, ChatRoomClientService chatRoomClientService, SimpMessagingTemplate messagingTemplate) {
        this.chatService = chatService;
        this.chatRoomClientService = chatRoomClientService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload final ChatMessageRequest message, final Principal principal) {
        if (principal == null || principal.getName() == null) {
            log.error("Principal is NULL! Cannot process message without sender information.");
            throw new IllegalArgumentException("Sender information is missing from Principal");
        }

        final Long senderId = Long.valueOf(principal.getName());
        message.setSenderId(senderId);

        if (isNewChat(message)) {
            handleNewChatRoomCreation(senderId, message);
        } else {
            processAndBroadcastMessage(message);
        }
    }

    @MessageMapping("/chat.seenMessage")
    public void messageSeen(@Payload final ChatMessageRequest message, final Principal principal) {
        if (principal == null || principal.getName() == null) {
            log.error("Principal is NULL! Cannot process seen event without receiver information.");
            throw new IllegalArgumentException("Receiver information is missing from Principal");
        }

        final Long receiverId = Long.valueOf(principal.getName());
        message.setReceiverId(receiverId);

        ChatMessageResponse seenConfirmation = chatService.processSeenEvent(message);
        messagingTemplate.convertAndSend(TOPIC_PREFIX + seenConfirmation.getChatId(), seenConfirmation);
    }

    // --- Private Helper Methods for Clarity and Separation of Concerns ---

    private boolean isNewChat(final ChatMessageRequest message) {
        return message.getChatId() == null || message.getChatId().isBlank();
    }

    private void handleNewChatRoomCreation(final Long senderId, final ChatMessageRequest message) {
        if (message.getReceiverId() == null) {
            log.error("Error: receiverId is null for a new chat request from senderId: {}", senderId);
            return;
        }

        log.info("chatId is null. Triggering new room creation for participants: {}, {}", senderId, message.getReceiverId());

        // ✅ CORRECTED: Create a request object that matches the chat-room-service's API contract.
        // This resolves the "400 Bad Request" error by sending the expected fields.
        CreateChatRoomRequest createRequest = new CreateChatRoomRequest();
        createRequest.setUserOneId(senderId);
        createRequest.setUserTwoId(message.getReceiverId());

        // Asynchronously call the Chat Room Service with proper error handling
        chatRoomClientService.createChatRoom(createRequest)
                .subscribe(
                        // Success Consumer
                        chatRoomResponse -> {
                            log.info("Chat room ensured. ChatId: {}, Was New: {}", chatRoomResponse.getChatId(), chatRoomResponse.isNew());
                            message.setChatId(chatRoomResponse.getChatId());
                            processAndBroadcastMessage(message);
                            notifyUsersOfNewRoom(senderId, message.getReceiverId(), chatRoomResponse);
                        },
                        // Error Consumer
                        error -> {
                            log.error("Failed to create new chat room for participants {} and {}.", senderId, message.getReceiverId(), error);
                        }
                );
    }

    private void processAndBroadcastMessage(final ChatMessageRequest message) {
        ChatMessageResponse processedMessage = chatService.processSendMessage(message);
        messagingTemplate.convertAndSend(TOPIC_PREFIX + processedMessage.getChatId(), processedMessage);
    }

    /**
     * Notifies both users about the newly created chat room.
     * This method now constructs a specific payload to ensure the client
     * receives both the new chatId and the list of participants.
     */
    private void notifyUsersOfNewRoom(final Long senderId, final Long receiverId, final ChatRoomResponse chatRoomResponse) {
        // ✅ IMPROVED: Create a dedicated notification payload. This ensures the client
        // receives all the data it needs (chatId and participantIds) to update its state,
        // which is exactly what your HTML/JS code is expecting.
        var notificationPayload = new Object() {
            public final String chatId = chatRoomResponse.getChatId();
            public final List<Long> participantIds = List.of(senderId, receiverId);
        };

        messagingTemplate.convertAndSendToUser(senderId.toString(), USER_QUEUE_ROOMS, notificationPayload);
        messagingTemplate.convertAndSendToUser(receiverId.toString(), USER_QUEUE_ROOMS, notificationPayload);
    }
}
>>>>>>> 6a1d08851ff8a3e2ea7a9353b54c701c7a204385
