package websocketgateway.dto;

// DTO for the request body sent to the Chat Room Service
public class CreateChatRoomRequest {
    // ✅ CORRECTED: Match the Chat-Room-Service's expected fields
    private Long userOneId;
    private Long userTwoId;

    // Getters and Setters
    public Long getUserOneId() {
        return userOneId;
    }

    public void setUserOneId(Long userOneId) {
        this.userOneId = userOneId;
    }

    public Long getUserTwoId() {
        return userTwoId;
    }

    public void setUserTwoId(Long userTwoId) {
        this.userTwoId = userTwoId;
    }
}