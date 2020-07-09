package paris8.wanted.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paris8.wanted.domain.CV;
import paris8.wanted.domain.Education;
import paris8.wanted.domain.Experience;
import paris8.wanted.domain.WantedUser;
import paris8.wanted.repo.CVRepo;
import paris8.wanted.repo.EducationRepo;
import paris8.wanted.repo.ExperienceRepo;


@RestController
@RequestMapping("cv")
public class CvController {
	private final EducationRepo educationRepo;
	private final ExperienceRepo experienceRepo;
	private final CVRepo cvRepo;
	
    @Autowired
    public CvController(EducationRepo educationRepo, ExperienceRepo experienceRepo, CVRepo cvRepo) {
		this.educationRepo = educationRepo;
		this.experienceRepo = experienceRepo;
		this.cvRepo = cvRepo;
    }
	
	@PutMapping
	public CV update(
			@RequestBody CV cv
	) {

			cv.update();
			
			if(cv.getEducations()!=null && cv.getEducations().size()>0) {
				for(Education edu: cv.getEducations()) {
					if(edu.getId() == null) {
						educationRepo.save(edu);	
					}
				
				}
			}
			
			if(cv.getExperiences()!=null && cv.getExperiences().size()>0) {
				for(Experience exp: cv.getExperiences()) {
					if(exp.getId() == null) {
						experienceRepo.save(exp);
					}
				}
			}
			
			if(cv.getId()!=null) {
				CV cvFromDB =cvRepo.getOne(cv.getId());
				BeanUtils.copyProperties(cv, cvFromDB, "id");
				cvFromDB.setEducations(cv.getEducations());
				cvFromDB.setExperiences(cv.getExperiences());
				
				return (CV)cvRepo.save(cvFromDB);
			}
			else {
				return (CV)cvRepo.save(cv);	
			}
	}
    
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") CV cv, @AuthenticationPrincipal WantedUser wantedUser) {
    	cvRepo.delete(cv);
    }
}