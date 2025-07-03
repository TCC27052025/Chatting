package in.ch.message_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chat_id")
public class ChatId {

    @Id
    @Column(name = "chat_uid", nullable = false)
    private String chatUid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", nullable = false)
    private MessageId message;

    public ChatId() {
        // Default constructor
    }

    public ChatId(String chatUid) {
        this.chatUid = chatUid;
    }

    public String getChatUid() {
        return chatUid;
    }

    public void setChatUid(String chatUid) {
        this.chatUid = chatUid;
    }

    public MessageId getMessage() {
        return message;
    }

    public void setMessage(MessageId message) {
        this.message = message;
    }
}

