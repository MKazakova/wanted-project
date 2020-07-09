package paris8.wanted.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import paris8.wanted.domain.Advertise;
import paris8.wanted.domain.Post;

public interface AdvertiseRepo extends JpaRepository<Advertise, Long> {

}
