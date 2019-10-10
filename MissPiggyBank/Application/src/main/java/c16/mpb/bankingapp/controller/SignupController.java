
package c16.mpb.bankingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignupController {

    @GetMapping(value = "/signup")
    public String signupHandler() {
        return "signup";
    }
}

