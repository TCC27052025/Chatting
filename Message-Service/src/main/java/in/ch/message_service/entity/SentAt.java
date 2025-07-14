package in.ch.message_service.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

    @Entity
    @Table(name = "sent_at")
    public class SentAt {
        @Id
        private String  messageId;

        @OneToOne(optional = false)
        @MapsId
        @JoinColumn(name = "message_id", nullable = false)
        private MessageId message;

        private LocalDateTime sentAt;

        public SentAt() {}

        public SentAt(MessageId message, LocalDateTime sentAt) {
            this.message = message;
            this.sentAt = sentAt;
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

        public LocalDateTime getSentAt() {
            return sentAt;
        }

        public void setSentAt(LocalDateTime sentAt) {
            this.sentAt = sentAt;
        }
    }