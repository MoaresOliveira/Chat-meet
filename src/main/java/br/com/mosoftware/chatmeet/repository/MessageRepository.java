package br.com.mosoftware.chatmeet.repository;

import br.com.mosoftware.chatmeet.domain.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
