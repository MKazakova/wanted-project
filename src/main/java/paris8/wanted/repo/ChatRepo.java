package paris8.wanted.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import paris8.wanted.domain.Chat;

public interface ChatRepo extends JpaRepository<Chat, Long> {
	public List<Chat> findByJsIdIn(List<Long> id);
	public List<Chat> findByHhIdIn(List<Long> id);
	public Chat findByJsId(Long id);
	public Chat findByHhId(Long id);
}
