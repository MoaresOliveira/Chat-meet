package br.com.mosoftware.chatmeet.repository;

import br.com.mosoftware.chatmeet.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
