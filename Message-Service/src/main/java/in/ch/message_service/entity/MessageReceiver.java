package in.ch.message_service.entity;

    import jakarta.persistence.*;

    @Entity
    @Table(name = "receiver_id")
    public class MessageReceiver {
        @Id
        private String  messageId;

        @OneToOne(optional = false)
        @MapsId
        @JoinColumn(name = "message_id", nullable = false)
        private MessageId message;

        private String receiverId;

        public MessageReceiver() {}


        public MessageReceiver(String receiverId) {
            this.receiverId = receiverId;
        }



        public MessageReceiver(MessageId message, String receiverId) {
            this.message = message;
            this.receiverId = receiverId;
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

        public String getReceiverId() {
            return receiverId;
        }

        public void setReceiverId(String receiverId) {
            this.receiverId = receiverId;
        }
    }