package in.ch.message_service.dto;

public class MessageSentEventDto {

    private String chatId;
    private Long senderId;
    private Long receiverId;
    private String content;
    private String messageType;
    private Long replyForMessageId;

    public MessageSentEventDto() {}

    public MessageSentEventDto(String chatId, Long senderId, Long receiverId,
                               String content, String messageType, Long replyForMessageId) {
        this.chatId = chatId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.messageType = messageType;
        this.replyForMessageId = replyForMessageId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Long getReplyForMessageId() {
        return replyForMessageId;
    }

    public void setReplyForMessageId(Long replyForMessageId) {
        this.replyForMessageId = replyForMessageId;
    }

    @Override
    public String toString() {
        return "MessageSentEventDto{" +
                "chatId='" + chatId + '\'' +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", content='" + content + '\'' +
                ", messageType='" + messageType + '\'' +
                ", replyForMessageId=" + replyForMessageId +
                '}';
    }
}
