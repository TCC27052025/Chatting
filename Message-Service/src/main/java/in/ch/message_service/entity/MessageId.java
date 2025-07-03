package in.ch.message_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "message_id")
public class MessageId {

    @Id
    @Column(name = "message_id", nullable = false, unique = true)
    private String messageId;

    public MessageId() {
        // Default constructor for JPA
    }

    public MessageId(String id) {
        this.messageId = id;
    }

    public String getId() {
        return messageId;
    }

    public void setId(String id) {
        this.messageId = id;
    }
}

