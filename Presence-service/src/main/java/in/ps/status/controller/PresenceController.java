package in.ps.status.controller;

import in.ps.status.dto.PresenceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1/presence")
public class PresenceController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping
    public PresenceResponse getPresence(@RequestParam String userId, @RequestParam String chatId) {

        // Redis keys
        String onlineKey = "online:" + userId;
        String sessionKey = "sessions:" + userId;
        String lastSeenKey = "last_seen:" + userId;
        String typingKey = "typing:" + userId + ":" + chatId;

        // Get Redis values
        String onlineValue = redisTemplate.opsForValue().get(onlineKey);
        String lastSeen = redisTemplate.opsForValue().get(lastSeenKey);
        String typingValue = redisTemplate.opsForValue().get(typingKey);
        Long sessionCount = redisTemplate.opsForSet().size(sessionKey);

        // Parse values
        boolean isOnline = "true".equalsIgnoreCase(onlineValue);
        boolean isTyping = "true".equalsIgnoreCase(typingValue);
        long activeSessions = (sessionCount != null) ? sessionCount : 0;

        // Build and return response
        return new PresenceResponse(
                userId,
                chatId,
                isOnline ? "ONLINE" : "OFFLINE",
                activeSessions,
                lastSeen,
                isTyping
        );
    }

    @PostMapping("/disconnect")
    public String disconnect(@RequestParam String userId) {
        String lastSeen = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        redisTemplate.delete("online:" + userId);
        redisTemplate.delete("sessions:" + userId);
        redisTemplate.opsForValue().set("last_seen:" + userId, lastSeen);

        // Optional: if chatId is dynamic and known, you can also delete typing keys
        // redisTemplate.delete("typing:" + userId + ":C101");

        return "User " + userId + " marked offline. Last seen set to " + lastSeen;
    }
}