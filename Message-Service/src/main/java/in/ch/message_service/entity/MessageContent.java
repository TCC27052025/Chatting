package in.ch.message_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "content")
public class MessageContent {

    @Id
    private String  messageId;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "message_id", nullable = false)
    private MessageId message;

    @Column(nullable = false)
    private String content;

    public MessageContent() {}

    public MessageContent(String content) {
        this.content = content;
    }

    // ✅ Correct constructor using MessageId entity
    public MessageContent(MessageId message, String content) {
        this.message = message;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
