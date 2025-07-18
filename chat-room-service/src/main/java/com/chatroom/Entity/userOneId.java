package com.chatroom.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_id_one")
public class userOneId {

    @Id
<<<<<<< HEAD
<<<<<<< HEAD
    @Column(name = "user_id")  // Primary key column
    private Long userIdOne;

    @OneToOne
    @JoinColumn(name = "chat_id", referencedColumnName = "chatId")  // Foreign key to chat table
    private Chat_Id chatid;

    // Getters and Setters
=======
=======
>>>>>>> 6a1d08851ff8a3e2ea7a9353b54c701c7a204385
    @Column(name = "user_id")
    private Long userIdOne;

    @OneToOne
    @JoinColumn(name = "chat_id", referencedColumnName = "chat_id")  // FIXED HERE
    private Chat_Id chatid;

<<<<<<< HEAD
>>>>>>> 6a1d08851ff8a3e2ea7a9353b54c701c7a204385
=======
>>>>>>> 6a1d08851ff8a3e2ea7a9353b54c701c7a204385
    public Long getUserIdOne() {
        return userIdOne;
    }

    public void setUserIdOne(Long userIdOne) {
        this.userIdOne = userIdOne;
    }

    public Chat_Id getChatid() {
        return chatid;
    }

    public void setChatid(Chat_Id chatid) {
        this.chatid = chatid;
    }
}
