package in.ch.message_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "media_url")
public class MediaUrl {
    @Id
    private String messageId;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "message_id", nullable = false)
    private MessageId message;

    @Column(name = "media_url")
    private String mediaUrl;

    public MediaUrl() {}

    public MediaUrl(MessageId message, String mediaUrl) {
        this.message = message;
        this.mediaUrl = mediaUrl;
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

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
}