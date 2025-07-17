package in.ps.status.dto;

// A single DTO to handle connect, disconnect, and typing events
public class PresenceRequest {
    private String userId;
    private String sessionId;
    private String chatId;
    private boolean isTyping;

    // Getters and Setters...
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    public String getChatId() { return chatId; }
    public void setChatId(String chatId) { this.chatId = chatId; }
    public boolean isTyping() { return isTyping; }
    public void setTyping(boolean typing) { isTyping = typing; }
}