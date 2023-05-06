package br.com.mosoftware.chatmeet.repository;

import br.com.mosoftware.chatmeet.domain.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
}
