package in.ch.message_service.repository;

import in.ch.message_service.entity.MessageSender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageSenderRepository extends JpaRepository<MessageSender, Long> {
}
