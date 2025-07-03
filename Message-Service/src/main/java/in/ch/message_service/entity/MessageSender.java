package in.ch.message_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sender_id")
public class MessageSender {
    @Id
    private String  messageId;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "message_id", nullable = false)
    private MessageId message;

    private String senderId;

    public MessageSender() {
    }

    public MessageSender(Long senderId ) {
        this.senderId = String.valueOf(senderId);
    }

    public MessageSender(MessageId message, String senderId) {
        this.message = message;
        this.senderId = senderId;
    }

    public String  getMessageId() {
        return messageId;
    }

    public MessageId getMessage() {
        return message;
    }

    public void setMessage(MessageId message) {
        this.message = message;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
}