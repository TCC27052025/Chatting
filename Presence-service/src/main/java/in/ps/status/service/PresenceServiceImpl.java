package in.ps.status.service;

import in.ps.status.dto.PresenceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Service
public class PresenceServiceImpl implements PresenceService {

    // Define constants for Redis keys to avoid "magic strings"
    private static final String KEY_ONLINE_USER = "online:%s";
    private static final String KEY_SESSIONS = "sessions:%s";
    private static final String KEY_LAST_SEEN = "last_seen:%s";
    private static final String KEY_TYPING = "typing:%s:%s"; // userId:chatId

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public PresenceServiceImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public PresenceResponse getPresence(String userId, String chatId) {
        String onlineKey = String.format(KEY_ONLINE_USER, userId);
        String sessionKey = String.format(KEY_SESSIONS, userId);
        String lastSeenKey = String.format(KEY_LAST_SEEN, userId);
        String typingKey = String.format(KEY_TYPING, userId, chatId);

        String onlineValue = redisTemplate.opsForValue().get(onlineKey);
        String lastSeen = redisTemplate.opsForValue().get(lastSeenKey);
        String typingValue = redisTemplate.opsForValue().get(typingKey);
        Long sessionCount = redisTemplate.opsForSet().size(sessionKey);

        boolean isOnline = "true".equalsIgnoreCase(onlineValue);
        boolean isTyping = "true".equalsIgnoreCase(typingValue);
        long activeSessions = (sessionCount != null) ? sessionCount : 0;

        return new PresenceResponse(
                userId,
                chatId,
                isOnline ? "ONLINE" : "OFFLINE",
                activeSessions,
                lastSeen,
                isTyping
        );
    }

    @Override
    public void connect(String userId, String sessionId) {
        String onlineKey = String.format(KEY_ONLINE_USER, userId);
        String sessionKey = String.format(KEY_SESSIONS, userId);

        redisTemplate.opsForValue().set(onlineKey, "true");
        redisTemplate.opsForSet().add(sessionKey, sessionId);
        redisTemplate.delete(String.format(KEY_LAST_SEEN, userId));
    }

    @Override
    public void disconnect(String userId, String sessionId) {
        String sessionKey = String.format(KEY_SESSIONS, userId);
        redisTemplate.opsForSet().remove(sessionKey, sessionId);

        Long sessionCount = redisTemplate.opsForSet().size(sessionKey);
        if (sessionCount == null || sessionCount == 0) {
            redisTemplate.delete(String.format(KEY_ONLINE_USER, userId));
            String lastSeen = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            redisTemplate.opsForValue().set(String.format(KEY_LAST_SEEN, userId), lastSeen);
        }
    }

    @Override
    public void setTypingStatus(String userId, String chatId, boolean isTyping) {
        String typingKey = String.format(KEY_TYPING, userId, chatId);
        if (isTyping) {
            redisTemplate.opsForValue().set(typingKey, "true", 5, TimeUnit.SECONDS);
        } else {
            redisTemplate.delete(typingKey);
        }
    }
}