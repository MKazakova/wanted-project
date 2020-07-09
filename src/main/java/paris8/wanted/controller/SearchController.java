package paris8.wanted.controller;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paris8.wanted.domain.Advertise;
import paris8.wanted.domain.CV;
import paris8.wanted.domain.Search;
import paris8.wanted.service.SearchService;

@RestController
@RequestMapping("searches")
public class SearchController {
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private SearchService searchService;
	
	@PostMapping("/cv")
	public List<CV> searchCV(
            @RequestBody Search search
    ) {
		return searchService.searchCorrespondingCV(search);
    }
	
	@PostMapping("/ad")
	public List<Advertise> searchAD(
            @RequestBody Search search
    ) {
		return searchService.searchCorrespondingAd(search);
    }
	
	@PostMapping("/ai/ad")
	public List<CV> authoSearchCV(
            @RequestBody Advertise add
    ) {
		Search search = new Search();
		search.setCity(add.getCity());
		search.setCountry(add.getCountry());
		search.setEducation(add.getBacPlusLevel());
		search.setCompetences(add.getCompetences());
		search.setPosition(add.getPosition());
		search.setLargeSearch(true);
		search.setLanguages(add.getLanguages());
		search.setExperience(add.getMonthsOfExperience());
		search.setSalary(add.getMaxSalary()==null?0:add.getMaxSalary());
		return searchService.searchAuthoCorrespondingCV(search);
    }
	
	@PostMapping("/ai/cv")
	public List<Advertise> authoSearchAD(
            @RequestBody CV cv
    ) {
		Search search = new Search();
		if(!cv.isReadyToMove()) {
			search.setCity(cv.getCity());
			search.setCountry(cv.getCountry());
		}
		search.setEducation(cv.getBacPlusLevel());
		search.setCompetences(cv.getCompetences());
		search.setPosition(cv.getPosition());
		search.setLargeSearch(true);
		search.setLanguages(cv.getLanguages());
		search.setExperience(cv.getMonthsOfExperience());
		search.setSalary(cv.getMinSalary());
		return searchService.searchAuthoCorrespondingAd(search);
    }
}
