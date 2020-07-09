package paris8.wanted.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import paris8.wanted.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Long>  {
	public List<Message> findByChatId(Long id);
}
