package c16.mpb.bankingapp.controller;

import c16.mpb.bankingapp.service.HibernateLab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DatabaseController {

    @Autowired
    HibernateLab lab;

    @GetMapping(value = "initdb")
    public String indexInitdbHandler(Model model) {
        lab.dbinit();
        return "index";
    }

    @GetMapping(value = "initdb2")
    public String indexInitdb2Handler(Model model) {
        lab.dbinit2();
        return "index";
    }
}
