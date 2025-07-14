package in.ch.message_service.repository;

import in.ch.message_service.entity.MessageReceiver;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageReceiverRepository extends JpaRepository<MessageReceiver, Long> {
    // Additional query methods can be defined here if needed
}
