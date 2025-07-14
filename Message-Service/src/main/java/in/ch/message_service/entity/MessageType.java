package in.ch.message_service.entity;

import in.ch.message_service.model.MessageTypeEnums;
import jakarta.persistence.*;


@Entity
@Table(name = "message_type")
public class MessageType {

    @Id
    private String  messageId;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "message_id", nullable = false)
    private MessageId message;

    @Enumerated(EnumType.STRING)
    @Column(name = "message_type", nullable = false)
    private MessageTypeEnums messageType;

    public MessageType() {
    }

    public MessageType(String messageType) {
        this.messageType = MessageTypeEnums.valueOf(messageType);
    }

    // ✅ DO NOT manually set messageId
    public MessageType(MessageId message, String messageType) {
        this.message = message;
        this.messageType = MessageTypeEnums.valueOf(messageType);
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

    public MessageTypeEnums getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageTypeEnums messageType) {
        this.messageType = messageType;
    }
}
