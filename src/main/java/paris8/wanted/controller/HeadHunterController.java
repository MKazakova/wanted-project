package paris8.wanted.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paris8.wanted.domain.HeadHunter;
import paris8.wanted.domain.UserRole;
import paris8.wanted.domain.WantedUser;
import paris8.wanted.repo.HeadHunterRepo;
import paris8.wanted.repo.WantedUserRepo;



@RestController
@RequestMapping("headHunter")
public class HeadHunterController {
	private final HeadHunterRepo headHunterRepo;
	private final WantedUserRepo wantedUserRepo;
    @Autowired
    public HeadHunterController(HeadHunterRepo headHunterRepo, WantedUserRepo wantedUserRepo) {
		this.headHunterRepo = headHunterRepo;
		this.wantedUserRepo = wantedUserRepo;
    }
	
	@PutMapping("{id}")
	public WantedUser update(
			@PathVariable("id") WantedUser wantedUserFromDB,
			@RequestBody HeadHunter headHunter
	) {

		
		
		headHunter.setWantedUserId(wantedUserFromDB.getId());
		
		if(wantedUserFromDB.getProfile()!=null) {
			HeadHunter hh = headHunterRepo.getOne(wantedUserFromDB.getProfile().getId()); 
			BeanUtils.copyProperties(headHunter, hh, "id");
			wantedUserFromDB.setUserProfile(hh);
		}
		else {
			wantedUserFromDB.setRole(UserRole.HH);
			headHunterRepo.save(headHunter);
			wantedUserFromDB.setUserProfile(headHunter);
		}
		
		return wantedUserRepo.save(wantedUserFromDB);
	}
	
}
