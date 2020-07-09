package paris8.wanted.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import paris8.wanted.domain.WantedUser;

@Controller
@RequestMapping("/profile")
public class ProfileController {   
  //  private final AnswerRepo answerRepo;
       
   /* @Autowired
    public HomeController(AnswerRepo answerRepo) {
        this.answerRepo = answerRepo;
    }*/

    
    @GetMapping("{choice}")
    public String choice(@PathVariable String choice) {
        return "index";
    }
    
    @GetMapping
    public String choice2() {
        return "index";
    }
}

