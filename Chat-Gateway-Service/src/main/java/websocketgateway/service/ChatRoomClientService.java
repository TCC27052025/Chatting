package websocketgateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import websocketgateway.dto.CreateChatRoomRequest;
import websocketgateway.dto.ChatRoomResponse;

@Service
public class ChatRoomClientService {

    private final WebClient chatRoomWebClient;

    @Autowired
    public ChatRoomClientService(WebClient chatRoomWebClient) {
        this.chatRoomWebClient = chatRoomWebClient;
    }

    /**
     * Calls the Chat Room Service to create a new chat room.
     * @param request The request containing participant IDs.
     * @return A Mono containing the response with the new chatId.
     */
    public Mono<ChatRoomResponse> createChatRoom(CreateChatRoomRequest request) {
        return chatRoomWebClient.post()
                .uri("/api/v1/chat-room")
                .body(Mono.just(request), CreateChatRoomRequest.class)
                .retrieve()
                .bodyToMono(ChatRoomResponse.class);
    }
}