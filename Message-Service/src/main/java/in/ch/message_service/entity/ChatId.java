package in.ch.message_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chat_id")
public class ChatId {

    @Id
<<<<<<< HEAD
    @Column(name = "chat_uid", nullable = false)
    private String chatUid;
=======
    @Column(name = "chat_id", nullable = false)
    private String chat_id;
>>>>>>> 6a1d08851ff8a3e2ea7a9353b54c701c7a204385

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", nullable = false)
    private MessageId message;

<<<<<<< HEAD
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
=======
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
>>>>>>> 6a1d08851ff8a3e2ea7a9353b54c701c7a204385
    }

    public MessageId getMessage() {
        return message;
    }

    public void setMessage(MessageId message) {
        this.message = message;
    }
}
<<<<<<< HEAD

=======
>>>>>>> 6a1d08851ff8a3e2ea7a9353b54c701c7a204385
