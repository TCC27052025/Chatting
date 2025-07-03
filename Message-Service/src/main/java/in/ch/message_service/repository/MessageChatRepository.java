package in.ch.message_service.repository;

import in.ch.message_service.entity.ChatId;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageChatRepository extends JpaRepository<ChatId, Long> {
}