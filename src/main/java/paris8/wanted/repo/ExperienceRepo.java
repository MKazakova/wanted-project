package paris8.wanted.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import paris8.wanted.domain.Experience;

public interface ExperienceRepo  extends JpaRepository<Experience, Long> {

}
