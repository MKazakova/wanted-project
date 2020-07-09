package paris8.wanted.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import paris8.wanted.domain.CountryName;


public interface CountryRepo extends JpaRepository<CountryName, Long> {

}
