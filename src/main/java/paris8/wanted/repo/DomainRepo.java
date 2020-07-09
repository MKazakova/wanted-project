package paris8.wanted.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import paris8.wanted.domain.Domain;


public interface DomainRepo extends JpaRepository<Domain, Long> {

}
