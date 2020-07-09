package paris8.wanted.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import paris8.wanted.config.WebSecurityConfig;
import paris8.wanted.domain.Chat;
import paris8.wanted.domain.Post;
import paris8.wanted.domain.UserRole;
import paris8.wanted.domain.WantedUser;
import paris8.wanted.repo.AnswerRepo;
import paris8.wanted.repo.ChatRepo;
import paris8.wanted.repo.CountryRepo;
import paris8.wanted.repo.DomainRepo;
import paris8.wanted.repo.LanguageRepo;
import paris8.wanted.repo.PostRepo;
import paris8.wanted.service.MailSender; 

@Controller
@RequestMapping("/")
public class HomeController {   

	@Autowired
	WebSecurityConfig ws;
	
	@Value("${spring.mode:prod}")
    private String mode;
	
	@Autowired
	private DomainRepo domainRepo;
	
	@Autowired
	private LanguageRepo languageRepo;
	
	@Autowired
	private ChatRepo chatRepo;
	
	@Autowired
	private AnswerRepo answerRepo;
	
	@Autowired
	private CountryRepo countryRepo;
	
	@Autowired
	private PostRepo postRepo;

    @GetMapping   
    public String main(Model model, @AuthenticationPrincipal WantedUser wantedUser) throws AuthenticationException, Exception {
        setData(model, wantedUser);
        return "index"; 
    }
    
    @GetMapping("*")
    public String refresh(Model model, @AuthenticationPrincipal WantedUser wantedUser, HttpServletRequest request) {
    	setData(model, wantedUser);
        return "index";
    }
    
    private void setData(Model model, WantedUser wantedUser) {
    	HashMap<Object, Object> data = new HashMap<>();
        data.put("user", wantedUser);
        if(wantedUser!=null && wantedUser.getUserProfile() != null) {
        	List<Post> posts = postRepo.findByAuthorId(wantedUser.getUserProfile().getId());
        	List<Chat> chats;
        	if(wantedUser.getRole()==UserRole.JS) {
        		data.put("cvs", posts);
        		chats = chatRepo.findByJsIdIn(posts.stream().map(p->p.getId()).collect(Collectors.toList()));
        		
        		for (Chat chat : chats) {
        			if(chat.getHhId()!=null) {
        				chat.setHh(postRepo.findById(chat.getHhId()).orElse(null));	
        			}
        		}
        		data.put("chats", chats);
        	}
        	else if(wantedUser.getRole()==UserRole.HH) {
        		data.put("annonces", posts);
        		chats = chatRepo.findByHhIdIn(posts.stream().map(p->p.getId()).collect(Collectors.toList()));
        		data.put("chats", chats);
        		for (Chat chat : chats) {
        			if(chat.getJsId()!=null) {
        				chat.setJs(postRepo.findById(chat.getJsId()).orElse(null));
        			}
        			
        		}
        	}
        	
        	data.put("requests", answerRepo.findByAuthorIdIn(posts.stream().map(p->p.getId()).collect(Collectors.toList())));
        	data.put("answers", answerRepo.findByPostIdIn(posts.stream().map(p->p.getId()).collect(Collectors.toList())));
        	
        }
        data.put("languages", languageRepo.findAll());
        data.put("countries", countryRepo.findAll());
        
        model.addAttribute("userInfo", data);
        model.addAttribute("domains", domainRepo.findAll());
        model.addAttribute("isDevMode", "dev".equals(mode));
    }
    
}
