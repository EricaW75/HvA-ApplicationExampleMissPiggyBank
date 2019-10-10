package c16.mpb.bankingapp.controller;

import c16.mpb.bankingapp.model.ExtraAccountHolderAuthorisationData;
import c16.mpb.bankingapp.service.AuthorizeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class EnterAuthorisationCodeController {

    @Autowired
    AuthorizeUser authorizeUser;

    @PostMapping(value = "authorisation_result")
    public String authorisationHandler(@ModelAttribute ExtraAccountHolderAuthorisationData data, HttpSession session, Model model) {
        String result;
        if (authorizeUser.validateCode(data)){
            result = "Gelukt! Account is gekoppeld.";
            authorizeUser.processAuthorization(data, session);
        } else {
            result = "Niet gelukt! Heb je de code echt goed ingevoerd?";
        }
        model.addAttribute("result", result);
        return "authorisation_result";
    }
}
