package paris8.wanted.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import paris8.wanted.domain.Post;

public interface PostRepo extends JpaRepository<Post, Long> {
	
	public List<Post> findByAuthorId(Long id);

}
