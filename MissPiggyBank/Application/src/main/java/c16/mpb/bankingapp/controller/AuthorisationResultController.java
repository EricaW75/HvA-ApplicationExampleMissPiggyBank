package c16.mpb.bankingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorisationResultController {

    @GetMapping("authorisation_result")
    public String authorisationHandler (){
        return "authorisation_result";
    }



}
