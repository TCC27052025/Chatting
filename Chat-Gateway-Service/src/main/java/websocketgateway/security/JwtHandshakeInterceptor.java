package websocketgateway.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.security.Principal;
import java.util.Map;
@Component
public class JwtHandshakeInterceptor implements HandshakeInterceptor {

    private final JwtUtil jwtUtil;

    public JwtHandshakeInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) {

       if (request instanceof ServletServerHttpRequest servletRequest) {
    HttpServletRequest req = servletRequest.getServletRequest();
    String token = req.getParameter("token");

    if (token != null && jwtUtil.validateJwtToken(token)) {
        Long userId = jwtUtil.getUserIdFromToken(token);

        // 👇 Put the user ID so it can be extracted in handshake handler
        attributes.put("userId", String.valueOf(userId));
        attributes.put("user", new Principal() {
            @Override
            public String getName() {
                return String.valueOf(userId); // This will be injected as principal.getName()
            }
        });

        System.out.println("✅ Valid JWT for user ID: " + userId);
        return true;
    }

    System.out.println("❌ Invalid or missing JWT: " + token);
}
        System.out.println("❌ WebSocket handshake failed due to invalid/missing JWT.");
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
        // No-op
    }
}
