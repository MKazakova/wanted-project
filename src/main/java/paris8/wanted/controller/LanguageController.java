package paris8.wanted.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paris8.wanted.domain.Language;
import paris8.wanted.domain.LangueName;
import paris8.wanted.repo.LanguageRepo;

@RestController
@RequestMapping("language")
public class LanguageController {
	private final LanguageRepo languageRepo;
	
	@Autowired
	public LanguageController(LanguageRepo languageRepo) {
		 this.languageRepo = languageRepo;
	}
	
	@PostMapping
    public LangueName create(
            @RequestBody LangueName language
    ) {
        return languageRepo.save(language);
    }
    
    @PutMapping("{id}")
	public LangueName update(
			@PathVariable("id") LangueName languageFromDB,
			@RequestBody LangueName language
	) {
    	BeanUtils.copyProperties(language, languageFromDB, "id");
		
		return languageRepo.save(languageFromDB);

	}
    
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") LangueName language) {
    	languageRepo.delete(language);
    }

}
