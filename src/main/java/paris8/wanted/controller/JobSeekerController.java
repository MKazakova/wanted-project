package paris8.wanted.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paris8.wanted.domain.JobSeeker;
import paris8.wanted.domain.UserRole;
import paris8.wanted.domain.WantedUser;
import paris8.wanted.repo.JobSeekerRepo;
import paris8.wanted.repo.WantedUserRepo;


@RestController
@RequestMapping("jobSeeker")
public class JobSeekerController {
	private final JobSeekerRepo jobSeekerRepo;
	private final WantedUserRepo wantedUserRepo;
	
    @Autowired
    public JobSeekerController(JobSeekerRepo jobSeekerRepo, WantedUserRepo wantedUserRepo) {
		this.jobSeekerRepo = jobSeekerRepo;
		this.wantedUserRepo = wantedUserRepo;
    }
	
	@PutMapping("{id}")
	public WantedUser update(
			@PathVariable("id") WantedUser wantedUserFromDB,
			@RequestBody JobSeeker jobSeeker
	) {

		
		
		jobSeeker.setWantedUserId(wantedUserFromDB.getId());
		
		if(wantedUserFromDB.getProfile()!=null) {
			JobSeeker js = jobSeekerRepo.getOne(wantedUserFromDB.getProfile().getId()); 
			BeanUtils.copyProperties(jobSeeker, js, "id");
			wantedUserFromDB.setUserProfile(js);
		}
		else {
			wantedUserFromDB.setRole(UserRole.JS);
			jobSeekerRepo.save(jobSeeker);
			wantedUserFromDB.setUserProfile(jobSeeker);
		}
		
		return wantedUserRepo.save(wantedUserFromDB);
	}
	
}
