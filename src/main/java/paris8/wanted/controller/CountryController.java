package paris8.wanted.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paris8.wanted.domain.CountryName;
import paris8.wanted.repo.CountryRepo;

@RestController
@RequestMapping("country")
public class CountryController {
	private final CountryRepo countryRepo;
	
	@Autowired
	public CountryController(CountryRepo countryRepo) {
		 this.countryRepo = countryRepo;
	}
	
	@PostMapping
    public CountryName create(
            @RequestBody CountryName country
    ) {
        return countryRepo.save(country);
    }
    
    @PutMapping("{id}")
	public CountryName update(
			@PathVariable("id") CountryName countryFromDB,
			@RequestBody CountryName country
	) {
    	BeanUtils.copyProperties(country, countryFromDB, "id");
		
		return countryRepo.save(countryFromDB);

	}
    
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") CountryName country) {
    	countryRepo.delete(country);
    }
}
