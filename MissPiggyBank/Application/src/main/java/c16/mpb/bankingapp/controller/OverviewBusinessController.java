package c16.mpb.bankingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("username")
public class OverviewBusinessController {

    @GetMapping(value = "overview_business")
    public String overviewBusinessHandler(Model model){
        return "overview_business";
    }
}
