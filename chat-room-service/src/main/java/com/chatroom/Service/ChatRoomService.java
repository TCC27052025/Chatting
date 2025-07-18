package com.chatroom.Service;

<<<<<<< HEAD
<<<<<<< HEAD
import com.chatroom.DTO.ChatRoomRequestDTO;
import com.chatroom.DTO.ChatRoomResponseDTO;

public interface ChatRoomService {
    ChatRoomResponseDTO createOrGetChatRoom(ChatRoomRequestDTO requestDTO);
}
=======
=======
>>>>>>> 6a1d08851ff8a3e2ea7a9353b54c701c7a204385
import com.chatroom.DTO.ChatRoomResponse;

public interface ChatRoomService {
    ChatRoomResponse findOrCreateChatRoom(Long userOneId, Long userTwoId);
<<<<<<< HEAD
}
>>>>>>> 6a1d08851ff8a3e2ea7a9353b54c701c7a204385
=======
}
>>>>>>> 6a1d08851ff8a3e2ea7a9353b54c701c7a204385
