package websocketgateway.dto;

import java.util.List;

// DTO for the response received from the Chat Room Service
public class ChatRoomResponse {
    // ✅ CORRECTED: Match the Chat-Room-Service's response fields
    private String chatId;
    private boolean isNew;
    // ✅ ADDED: We need this to notify the correct users.
    private List<Long> participantIds;

    // Getters and Setters
    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public List<Long> getParticipantIds() {
        return participantIds;
    }

    public void setParticipantIds(List<Long> participantIds) {
        this.participantIds = participantIds;
    }
}