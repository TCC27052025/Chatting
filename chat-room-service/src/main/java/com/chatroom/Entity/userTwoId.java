package com.chatroom.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_id_two")
public class userTwoId {

    @Id
<<<<<<< HEAD
<<<<<<< HEAD
    @Column(name = "user_id")  // Primary key column
    private Long userIdTwo;

    @OneToOne
    @JoinColumn(name = "chat_id", referencedColumnName = "chatId")  // Foreign key to Chat_Id table
    private Chat_Id chatid;

    // Getters and Setters
=======
=======
>>>>>>> 6a1d08851ff8a3e2ea7a9353b54c701c7a204385
    @Column(name = "user_id")
    private Long userIdTwo;

    @OneToOne
    @JoinColumn(name = "chat_id", referencedColumnName = "chat_id")  // FIXED HERE
    private Chat_Id chatid;

<<<<<<< HEAD
>>>>>>> 6a1d08851ff8a3e2ea7a9353b54c701c7a204385
=======
>>>>>>> 6a1d08851ff8a3e2ea7a9353b54c701c7a204385
    public Long getUserIdTwo() {
        return userIdTwo;
    }

    public void setUserIdTwo(Long userIdTwo) {
        this.userIdTwo = userIdTwo;
    }

    public Chat_Id getChatid() {
        return chatid;
    }

    public void setChatid(Chat_Id chatid) {
        this.chatid = chatid;
    }
}
