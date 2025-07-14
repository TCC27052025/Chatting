package in.ch.message_service.repository;

import in.ch.message_service.entity.MessageId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MessageIdRepository extends JpaRepository<MessageId, Long> {
    Optional<MessageId> findById(String messageId);
}
