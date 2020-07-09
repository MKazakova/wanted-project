package paris8.wanted.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import paris8.wanted.domain.Answer;


public interface AnswerRepo extends JpaRepository<Answer, Long> {
	public List<Answer> findByAuthorId(Long id);
	public List<Answer> findByPostId(Long id);
	public List<Answer> findByPostIdIn(List <Long> id);
	public List<Answer> findByAuthorIdIn(List<Long> collect);
}
