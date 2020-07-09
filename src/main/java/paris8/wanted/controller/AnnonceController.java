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

import paris8.wanted.domain.Advertise;
import paris8.wanted.domain.Answer;
import paris8.wanted.domain.HeadHunter;
import paris8.wanted.domain.JobSeeker;
import paris8.wanted.domain.Post;
import paris8.wanted.domain.WantedUser;
import paris8.wanted.repo.AdvertiseRepo;
import paris8.wanted.repo.AnswerRepo;
import paris8.wanted.repo.HeadHunterRepo;
import paris8.wanted.repo.PostRepo;

@RestController
@RequestMapping("annonce")
public class AnnonceController {
	private final HeadHunterRepo headHunterRepo;
	private final AdvertiseRepo annonceRepo;
	
	@Autowired
	private AnswerRepo answerRepo;
	
    @Autowired
    public AnnonceController(HeadHunterRepo headHunterRepo, AdvertiseRepo annonceRepo) {
		this.annonceRepo = annonceRepo;
		this.headHunterRepo = headHunterRepo;
    }
	
	@PutMapping
	public Advertise update(
			@RequestBody Advertise a
	) {
			if(a.getId()!=null) {
				Advertise annonceFromDB = (Advertise)annonceRepo.getOne(a.getId());
				BeanUtils.copyProperties(a, annonceFromDB, "id");
				return annonceRepo.save(annonceFromDB);
			}
			else {
				return annonceRepo.save(a);	
			}
		
	}
	
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Advertise annonce) {
    	for(Answer answer: answerRepo.findByAuthorId(annonce.getId())) {
    		answerRepo.delete(answer);
    	}
    	for(Answer answer: answerRepo.findByPostId(annonce.getId())) {
    		answerRepo.delete(answer);
    	}
    	annonceRepo.delete(annonce);
    }
}
