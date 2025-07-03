package in.ch.message_service.repository;

import in.ch.message_service.entity.SentAt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SentAtRepository extends JpaRepository<SentAt, Long> {
}
