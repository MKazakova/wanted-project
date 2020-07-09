package paris8.wanted.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import paris8.wanted.domain.JobSeeker;

public interface JobSeekerRepo extends JpaRepository<JobSeeker, Long>{

}
