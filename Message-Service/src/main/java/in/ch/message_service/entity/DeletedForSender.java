package in.ch.message_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "deleted_for_sender")
public class DeletedForSender {
    @Id
    private String messageId;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "message_id", nullable = false)
    private MessageId message;

    @Column(name = "deleted")
    private boolean deleted;

    public DeletedForSender() {
    }

    public DeletedForSender(MessageId message, boolean deleted) {
        this.message = message;
        this.deleted = deleted;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}