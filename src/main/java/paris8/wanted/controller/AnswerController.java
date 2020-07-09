package paris8.wanted.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paris8.wanted.domain.Answer;
import paris8.wanted.domain.Domain;
import paris8.wanted.domain.Post;
import paris8.wanted.domain.Status;
import paris8.wanted.repo.AnswerRepo;
import paris8.wanted.repo.HeadHunterRepo;
import paris8.wanted.repo.JobSeekerRepo;
import paris8.wanted.repo.PostRepo;
import paris8.wanted.repo.WantedUserRepo;
import paris8.wanted.service.MailSender;

@RestController
@RequestMapping("answer")
public class AnswerController {
	private final AnswerRepo answerRepo;
	
    @Autowired
    private MailSender mailSender;

    @Autowired
    private PostRepo postRepo;
    
    @Autowired
    public AnswerController(AnswerRepo answerRepo) {
		this.answerRepo = answerRepo;
    }
	
    @PostMapping
    public Answer create(
            @RequestBody Answer answer
    ) {
    	answer.setStatus(Status.SENT);
    	Answer sentAnswer = answerRepo.save(answer);
    	Post post = postRepo.getOne(sentAnswer.getPostId());
    	Post author = postRepo.getOne(sentAnswer.getAuthorId());
    	try {
    		mailSender.send(post.getEmail(), "Vous avez une nouvelle reponse", "Cher utilisateur! Vous avez une nouvelle reponse"+
    					" de la part de "+author.getEmail()+".\nLa regarder : https://project-maria.herokuapp.com/answers");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
        return sentAnswer;
    }
    
    @GetMapping
    public List<Answer> list(
           
    ) {
        return answerRepo.findAll();
    }
    
    @PutMapping("{id}")
   	public Answer update(
   			@PathVariable("id") Answer answerFromDB,
   			@RequestBody Answer answer
   	) {
    	answerFromDB.setStatus(answer.getStatus());
    	Answer sentAnswer = answerRepo.save(answerFromDB);
    	Post author = postRepo.getOne(sentAnswer.getAuthorId());
    	Post post = postRepo.getOne(sentAnswer.getPostId());
    	try {
    		mailSender.send(author.getEmail(), "Le statut de votre requete est change", "Cher utilisateur! Vous avez repondu a l'annonce "+
    					post.getPosition()+".L'autheur de cette annonce a change le statut de votre requete a "+sentAnswer.getStatus()+".\nLe regarder : https://project-maria.herokuapp.com/requests");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
        return sentAnswer;
   	}

}