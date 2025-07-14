package in.ch.message_service.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "delivered_at")
public class DeliveredAt {
    @Id
    private String messageId;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "message_id", nullable = false)
    private MessageId message;

    private LocalDateTime deliveredAt;

    public DeliveredAt() {
    }

    public DeliveredAt(MessageId message, LocalDateTime deliveredAt) {
        this.message = message;
        this.deliveredAt = deliveredAt;
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

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
    }
}