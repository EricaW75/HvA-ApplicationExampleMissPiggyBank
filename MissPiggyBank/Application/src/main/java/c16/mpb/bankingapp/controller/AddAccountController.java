package c16.mpb.bankingapp.controller;

import c16.mpb.bankingapp.model.Customer;
import c16.mpb.bankingapp.model.dao.CustomerDao;
import c16.mpb.bankingapp.model.dao.UserDao;
import c16.mpb.bankingapp.service.AddAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("firstName")
public class AddAccountController {


    @Autowired
    AddAccount addAccount;

    @Autowired
    UserDao userDao;

    @Autowired
    CustomerDao customerDao;


    @GetMapping(value = "make_account")
    public String addAccountHandler(HttpSession session) {
        String  currentUsername = session.getAttribute("sessionUserName").toString();
        Customer currentCustomer = customerDao.findByUsername(currentUsername);
        addAccount.makeNewAccount(currentCustomer);
        return "add_account_success";
    }
}

