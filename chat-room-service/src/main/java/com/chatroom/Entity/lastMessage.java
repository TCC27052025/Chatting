package com.chatroom.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "last_message")
public class lastMessage {

    @Id
<<<<<<< HEAD
    private Long msgId;
    public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	@OneToOne
    @MapsId
    @JoinColumn(name = "chat_id", referencedColumnName = "chatId")
=======
    @Column(name = "chat_id", nullable = false, length = 255)
    private String chatId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "chat_id", referencedColumnName = "chat_id")
>>>>>>> 6a1d08851ff8a3e2ea7a9353b54c701c7a204385
    private Chat_Id chatid;

    @Column(name = "msg")
    private String msg;

<<<<<<< HEAD
    // Getters and Setters
=======
    public lastMessage() {}

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

>>>>>>> 6a1d08851ff8a3e2ea7a9353b54c701c7a204385
    public Chat_Id getChatid() {
        return chatid;
    }

    public void setChatid(Chat_Id chatid) {
        this.chatid = chatid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
<<<<<<< HEAD
=======

>>>>>>> 6a1d08851ff8a3e2ea7a9353b54c701c7a204385
