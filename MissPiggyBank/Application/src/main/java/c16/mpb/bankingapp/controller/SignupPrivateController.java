package c16.mpb.bankingapp.controller;

import c16.mpb.bankingapp.model.BankAccount;
import c16.mpb.bankingapp.model.Customer;
import c16.mpb.bankingapp.model.User;
import c16.mpb.bankingapp.model.dao.BankAccountDao;
import c16.mpb.bankingapp.model.dao.CustomerDao;
import c16.mpb.bankingapp.model.dao.UserDao;
import c16.mpb.bankingapp.service.AddAccount;
import c16.mpb.bankingapp.service.BsnChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("firstName")
@RequestMapping(path="/private")
public class SignupPrivateController {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    UserDao userDao;

    @Autowired
    AddAccount addAccount;

    @Autowired
    BankAccountDao bankAccountDao;

    @Autowired
    BsnChecker bsnChecker;

    @GetMapping(value = "/signup")
    public String signUpPrivateHandler(Model model) {
        Customer customer = new Customer();
        model.addAttribute(customer);
        return "private/signupform";
    }

    @PostMapping(value = "/signup")
    public String signUpPrivateFormHandler(@ModelAttribute Customer customer, Model model) {
        model.addAttribute("customer", customer);
        String username = customer.getUsername();
        List<User> userList = userDao.findByUsername(username);
        boolean validBsn = bsnChecker.isValidBsn(customer.getBsn());
        if(validBsn && userList.size() == 0) {
            customer.setRole("Customer");
            addAccount.makeNewAccount(customer);
            String firstName = customer.getFirstName();
            model.addAttribute("firstName", firstName);
            BankAccount newlyCreatedBankAccount = bankAccountDao.findByOwnerId(customer.getUserId()).get(0);
            model.addAttribute("iban", newlyCreatedBankAccount.getIban());
            return "signup_completed";
        } else if(!validBsn) {
            model.addAttribute("bsnError","Je BSN is niet correct. Voer een geldig BSN in met 9 cijfers.");
            return "private/signupform";
        } else {
            model.addAttribute("usernameError","Deze username bestaat al, kies a.u.b. een andere.");
            return "private/signupform";
        }
    }


}



