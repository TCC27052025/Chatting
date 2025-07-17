package in.ps.status.controller;

<<<<<<< HEAD
import in.ps.status.dto.PresenceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
=======
import in.ps.status.dto.PresenceRequest;
import in.ps.status.dto.PresenceResponse;
import in.ps.status.service.PresenceService; // Import the service
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
>>>>>>> 6a1d08851ff8a3e2ea7a9353b54c701c7a204385

@RestController
@RequestMapping("/api/v1/presence")
public class PresenceController {

<<<<<<< HEAD
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
=======
    private static final Logger log = LoggerFactory.getLogger(PresenceController.class);

    private final PresenceService presenceService; // Inject the service

    @Autowired
    public PresenceController(PresenceService presenceService) {
        this.presenceService = presenceService;
    }

    @GetMapping
    public PresenceResponse getPresence(@RequestParam String userId, @RequestParam String chatId) {
        return presenceService.getPresence(userId, chatId);
    }

    @PostMapping("/connect")
    public ResponseEntity<Void> connect(@RequestBody PresenceRequest request) {
        log.info("CONNECT event for userId: {}, sessionId: {}", request.getUserId(), request.getSessionId());
        presenceService.connect(request.getUserId(), request.getSessionId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/disconnect")
    public ResponseEntity<Void> disconnect(@RequestBody PresenceRequest request) {
        log.info("DISCONNECT event for userId: {}, sessionId: {}", request.getUserId(), request.getSessionId());
        presenceService.disconnect(request.getUserId(), request.getSessionId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/typing")
    public ResponseEntity<Void> setTypingStatus(@RequestBody PresenceRequest request) {
        log.info("TYPING event for userId: {}, chatId: {}, isTyping: {}", request.getUserId(), request.getChatId(), request.isTyping());
        presenceService.setTypingStatus(request.getUserId(), request.getChatId(), request.isTyping());
        return ResponseEntity.ok().build();
>>>>>>> 6a1d08851ff8a3e2ea7a9353b54c701c7a204385
    }
}