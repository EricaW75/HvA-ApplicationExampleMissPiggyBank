package c16.mpb.bankingapp.controller;

import c16.mpb.bankingapp.model.BankAccount;
import c16.mpb.bankingapp.model.BusinessCustomer;
import c16.mpb.bankingapp.model.User;
import c16.mpb.bankingapp.model.dao.BankAccountDao;
import c16.mpb.bankingapp.model.dao.BusinessCustomerDao;
import c16.mpb.bankingapp.model.dao.UserDao;
import c16.mpb.bankingapp.service.AddAccount;
import c16.mpb.bankingapp.service.BsnChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Erica & Inge
@Controller
@SessionAttributes("firstName")
@RequestMapping(path="/business") //noun --> doe je zodat je een mooi pad krijgt: business/signup
public class SignupBusinessController {

    @Autowired
    BusinessCustomerDao businessCustomerDao;

    @Autowired
    UserDao userDao;

    @Autowired
    BankAccountDao bankAccountDao;

    @Autowired
    AddAccount addAccount;

    @Autowired
    BsnChecker bsnChecker;


    @GetMapping(value = "/signup") 
    public String signUpBusinessHandler(Model model) { 
        BusinessCustomer businessCustomer = new BusinessCustomer();
        model.addAttribute("customer", businessCustomer);
        return "business/signupform";
    }

    @PostMapping(value = "/signup")
    public String signUpBusinessFormHandler(@ModelAttribute BusinessCustomer businessCustomer, Model model) {
        model.addAttribute("customer", businessCustomer);
        String username = businessCustomer.getUsername();
        List<User> userList = userDao.findByUsername(username);
        boolean validBsn = bsnChecker.isValidBsn(businessCustomer.getBsn());

        if (validBsn && userList.size() == 0 ) {
            businessCustomer.setRole("Customer");
            addAccount.makeNewAccount(businessCustomer);
            BankAccount newlyCreatedBankAccount = bankAccountDao.findByOwnerId(businessCustomer.getUserId()).get(0);
            model.addAttribute("iban",newlyCreatedBankAccount.getIban());
            String firstName = businessCustomer.getFirstName();
            model.addAttribute("firstName", firstName);
            return "signup_completed";

        } else if (!validBsn) {
            model.addAttribute("bsnError", "Je BSN is niet correct. Voer een geldig BSN in met 9 cijfers.");
            return "business/signupform";
        } else {
            model.addAttribute("usernameError", "Deze username bestaat al, kies a.u.b. een andere.");
            return "business/signupform";
        }
    }
}
