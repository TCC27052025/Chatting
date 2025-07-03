package websocketgateway.dto;

public class ChatMessageRequest {
    private String chatId;
    private Long senderId;
    private Long receiverId;
    private String content;
    private String messageType;
    private String replyForMessageId;

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

    public String getReplyForMessageId() {
        return replyForMessageId;
    }

    public void setReplyForMessageId(String replyForMessageId) {
        this.replyForMessageId = replyForMessageId;
    }
}
