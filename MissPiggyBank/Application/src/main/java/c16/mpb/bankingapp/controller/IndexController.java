package c16.mpb.bankingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import c16.mpb.bankingapp.model.User;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("username")
public class IndexController {


    @GetMapping(value = "login")
    public String indexLoginHandler(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping(value = "index")
    public String indexGetHandler(Model model) {
        return "index";
    }
}