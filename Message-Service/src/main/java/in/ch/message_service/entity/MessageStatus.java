package in.ch.message_service.entity;

import in.ch.message_service.model.MessageStatusEnums;
import jakarta.persistence.*;

@Entity
@Table(name = "status")
public class MessageStatus {

    @Id
    private String messageId;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "message_id", nullable = false)
    private MessageId message;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MessageStatusEnums status;

    public MessageStatus() {
    }

    public MessageStatus(MessageId message, MessageStatusEnums status) {
        this.message = message;
        this.status = status;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public MessageId getMessage() {
        return message;
    }

    public void setMessage(MessageId message) {
        this.message = message;
    }

    public MessageStatusEnums getStatus() {
        return status;
    }

    public void setStatus(MessageStatusEnums status) {
        this.status = status;
    }
}