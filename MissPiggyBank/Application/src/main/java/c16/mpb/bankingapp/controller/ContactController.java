package c16.mpb.bankingapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    @GetMapping(value = "contact")
    public String contactHandler() {
        return "contact";
    }
}
