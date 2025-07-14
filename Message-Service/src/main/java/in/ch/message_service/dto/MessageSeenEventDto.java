package in.ch.message_service.dto;

public class MessageSeenEventDto {
    private Long messageId;
    private Long chatId;
    private Long receiverId;

    // Getters and setters...

    public MessageSeenEventDto() {
    }

    public MessageSeenEventDto(Long messageId, Long chatId, Long receiverId) {
        this.messageId = messageId;
        this.chatId = chatId;
        this.receiverId = receiverId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }
}
