package paris8.wanted.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import paris8.wanted.domain.Education;


public interface EducationRepo extends JpaRepository<Education, Long>  {

}
