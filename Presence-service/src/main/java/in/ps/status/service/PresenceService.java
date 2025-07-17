package in.ps.status.service;

import in.ps.status.dto.PresenceResponse;

public interface PresenceService {

    /**
     * Retrieves the complete presence status for a user in a specific chat.
     */
    PresenceResponse getPresence(String userId, String chatId);

    /**
     * Marks a user as online and adds their session.
     */
    void connect(String userId, String sessionId);

    /**
     * Marks a user as offline, removing their session and updating last_seen if needed.
     */
    void disconnect(String userId, String sessionId);

    /**
     * Sets or clears a user's typing status in a specific chat.
     */
    void setTypingStatus(String userId, String chatId, boolean isTyping);
}