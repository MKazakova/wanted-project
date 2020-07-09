package paris8.wanted.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paris8.wanted.domain.Domain;
import paris8.wanted.domain.JobSeeker;
import paris8.wanted.domain.WantedUser;
import paris8.wanted.repo.DomainRepo;

@RestController
@RequestMapping("domain")
public class DomainController {
	private final DomainRepo domainRepo;

	
    @Autowired
    public DomainController(DomainRepo domainRepo) {
		this.domainRepo = domainRepo;
    }
	
    @PostMapping
    public Domain create(
            @RequestBody Domain domain, @AuthenticationPrincipal WantedUser wantedUser
    ) {
    	
        return domainRepo.save(domain);
    }
    
    @GetMapping
    public List<Domain> list(
           
    ) {
    	
        return domainRepo.findAll();
    }
    
    @PutMapping("{id}")
	public Domain update(
			@PathVariable("id") Domain domainFromDB,
			@RequestBody Domain domain
	) {
    	BeanUtils.copyProperties(domain, domainFromDB, "id");
		
		return domainRepo.save(domainFromDB);

	}
    
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Domain domain) {
    	domainRepo.delete(domain);
    }

}
