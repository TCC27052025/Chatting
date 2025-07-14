package in.ch.message_service.repository;

import in.ch.message_service.entity.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageStatusRepository extends JpaRepository<MessageStatus, Long> {
}
