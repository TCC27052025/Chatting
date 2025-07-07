package in.ch.message_service.dto;

import in.ch.message_service.model.MessageStatusEnums;
import in.ch.message_service.model.MessageTypeEnums;

import java.time.LocalDateTime;

public class MessageDeliverEventDto {
    private Long messageId;
    private Long chatId;
    private Long senderId;
    private Long receiverId;
    private String content;
    private MessageTypeEnums messageType;
    private Long replyToMessageId;
    private MessageStatusEnums status;
    private LocalDateTime sentAt;
    private LocalDateTime deliveredAt;
    private String mediaUrl;
    private Boolean deleted;

    // Getters and setters...

    public MessageDeliverEventDto() {
    }

    public MessageDeliverEventDto(Long messageId, Long chatId, Long senderId, Long receiverId, String content,
                                  MessageTypeEnums messageType, Long replyToMessageId, MessageStatusEnums status,
                                  LocalDateTime sentAt, LocalDateTime deliveredAt, String mediaUrl, Boolean deleted) {
        this.messageId = messageId;
        this.chatId = chatId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.messageType = messageType;
        this.replyToMessageId = replyToMessageId;
        this.status = status;
        this.sentAt = sentAt;
        this.deliveredAt = deliveredAt;
        this.mediaUrl = mediaUrl;
        this.deleted = deleted;
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

    public MessageTypeEnums getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageTypeEnums messageType) {
        this.messageType = messageType;
    }

    public Long getReplyToMessageId() {
        return replyToMessageId;
    }

    public void setReplyToMessageId(Long replyToMessageId) {
        this.replyToMessageId = replyToMessageId;
    }

    public MessageStatusEnums getStatus() {
        return status;
    }

    public void setStatus(MessageStatusEnums status) {
        this.status = status;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}

