package paris8.wanted.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import paris8.wanted.domain.LangueName;

public interface LanguageRepo extends JpaRepository<LangueName, Long>{

}
