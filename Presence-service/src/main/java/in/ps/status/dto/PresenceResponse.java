package in.ps.status.dto;

public class PresenceResponse {
    private String userId;
    private String chatId;
    private String status;
    private long activeSessions;
    private String lastSeen;
    private boolean typing;

    public PresenceResponse(String userId, String chatId, String status, long activeSessions, String lastSeen, boolean typing) {
        this.userId = userId;
        this.chatId = chatId;
        this.status = status;
        this.activeSessions = activeSessions;
        this.lastSeen = lastSeen;
        this.typing = typing;
    }

    public String getUserId() { return userId; }
    public String getChatId() { return chatId; }
    public String getStatus() { return status; }
    public long getActiveSessions() { return activeSessions; }
    public String getLastSeen() { return lastSeen; }
    public boolean isTyping() { return typing; }
}
