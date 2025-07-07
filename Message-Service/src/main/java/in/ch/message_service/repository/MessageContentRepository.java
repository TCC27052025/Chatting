package in.ch.message_service.repository;

import in.ch.message_service.entity.MessageContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageContentRepository extends JpaRepository<MessageContent, Long> {
}
