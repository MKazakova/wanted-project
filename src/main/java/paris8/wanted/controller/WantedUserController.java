package paris8.wanted.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import paris8.wanted.domain.HeadHunter;
import paris8.wanted.domain.UserProfile;
import paris8.wanted.domain.UserRole;
import paris8.wanted.domain.WantedUser;
import paris8.wanted.repo.WantedUserRepo;

@RestController
@RequestMapping("wantedUser")
public class WantedUserController {
	private final WantedUserRepo wantedUserRepo;

    @Autowired
    public WantedUserController(WantedUserRepo wantedUserRepo) {
        this.wantedUserRepo = wantedUserRepo;
    }
    
	@PostMapping
	public WantedUser create(@RequestBody WantedUser wantedUser) {
		return wantedUserRepo.save(wantedUser);
	}
	
	@PutMapping("{id}")
	public WantedUser update(
			@PathVariable("id") WantedUser wantedUserFromDB,
			@RequestBody WantedUser wantedUser
	) {
		BeanUtils.copyProperties(wantedUser, wantedUserFromDB, "id");
		return wantedUserRepo.save(wantedUserFromDB);
	}
	
	@GetMapping
    public List<WantedUser> allusers(@AuthenticationPrincipal WantedUser wantedUser) {
		if(wantedUser.getRole()==UserRole.ADMIN) {
			return wantedUserRepo.findAll();
		}
        return null;
    }
	
}
