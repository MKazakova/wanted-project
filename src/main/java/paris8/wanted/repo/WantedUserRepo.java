package paris8.wanted.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import paris8.wanted.domain.WantedUser;

public interface WantedUserRepo extends JpaRepository<WantedUser, String>{

}
