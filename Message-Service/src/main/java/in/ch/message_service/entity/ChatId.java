package in.ch.message_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chat_id")
public class ChatId {

    @Id
    @Column(name = "chat_id", nullable = false)
    private String chat_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", nullable = false)
    private MessageId message;

    // ✅ Default constructor required by JPA
    public ChatId() {
    }

    // ✅ Constructor to initialize only chat_id
    public ChatId(String chat_id) {
        this.chat_id = chat_id;
    }

    // ✅ Constructor to initialize both fields
    public ChatId(String chat_id, MessageId message) {
        this.chat_id = chat_id;
        this.message = message;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public MessageId getMessage() {
        return message;
    }

    public void setMessage(MessageId message) {
        this.message = message;
    }
}
