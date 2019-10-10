package c16.mpb.bankingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignupCompletedController {

    @GetMapping(value = "/")
    public String signupCompletedHandler(Model model) {
        return "index";
    }
}
