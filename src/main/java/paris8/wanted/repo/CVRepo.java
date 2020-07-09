package paris8.wanted.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import paris8.wanted.domain.CV;

public interface CVRepo extends JpaRepository<CV, Long> {

}
