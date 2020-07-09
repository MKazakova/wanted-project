package paris8.wanted.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import paris8.wanted.domain.UserProfile;


public interface UserProfileRepo extends JpaRepository<UserProfile, Long> {

}
