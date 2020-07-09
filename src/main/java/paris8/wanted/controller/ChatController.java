package paris8.wanted.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import paris8.wanted.domain.Advertise;
import paris8.wanted.domain.Chat;
import paris8.wanted.domain.Message;
import paris8.wanted.domain.Views;
import paris8.wanted.repo.ChatRepo;
import paris8.wanted.repo.MessageRepo;
import paris8.wanted.repo.PostRepo;

@RestController
@RequestMapping("chat")
public class ChatController {
    private final ChatRepo chatRepo;

    @Autowired
    private PostRepo postRepo;
    
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    public ChatController(ChatRepo chatRepo) {
        this.chatRepo = chatRepo;
    }
    
	@Autowired
	private EntityManager entityManager;

    @PostMapping
    public Chat create(@RequestBody Chat chat) {
    	Chat chatFromDB;
    	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Chat> criteriaQuery = criteriaBuilder.createQuery(Chat.class);
		Root<Chat> itemRoot = criteriaQuery.from(Chat.class);
		criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(itemRoot.get("jsId"), chat.getJsId()), 
				criteriaBuilder.equal(itemRoot.get("hhId"), chat.getHhId())));
		List<Chat> result = entityManager.createQuery(criteriaQuery).getResultList();
		chatFromDB = result.size()>0?result.get(0):chatRepo.save(chat.setMessagesReturnChat(new ArrayList<>()));
    	return chatFromDB;
    }
    
    @GetMapping("/HH/{id}")
    public Chat findByJsId(@PathVariable("id") Long id) {
    	return chatRepo.findByJsId(id);
    }
    
    @GetMapping("/JS/{id}")
    public Chat findByHhId(@PathVariable("id") Long id) {
    	return chatRepo.findByHhId(id);
    }

    @MessageMapping("/changeMessage/{id}")
    @SendTo("/topic/activity/{id}")
    public Message chat(Message message) {
    	Chat chat = chatRepo.getOne(message.getChatId());
    	message.setCreationDate(LocalDateTime.now());
    	Message savedMessage = messageRepo.save(message);
    	if(chat.getMessages()==null) {
    		chat.setMessages(new ArrayList<>());
    	}
    	chat.getMessages().add(savedMessage);
    	chatRepo.save(chat);
        return savedMessage;
    }
}
