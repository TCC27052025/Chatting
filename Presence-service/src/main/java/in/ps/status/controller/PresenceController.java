package in.ps.status.controller;

import in.ps.status.dto.PresenceRequest;
import in.ps.status.dto.PresenceResponse;
import in.ps.status.service.PresenceService; // Import the service
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/presence")
public class PresenceController {

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
    }
}