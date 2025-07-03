package in.ch.message_service.repository;

import in.ch.message_service.entity.MessageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageTypeRepository extends JpaRepository<MessageType, Long> {
}
