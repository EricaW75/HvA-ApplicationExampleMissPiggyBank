package c16.mpb.bankingapp.controller;

import c16.mpb.bankingapp.model.*;
import c16.mpb.bankingapp.model.dao.BankAccountDao;
import c16.mpb.bankingapp.model.dao.CustomerDao;
import c16.mpb.bankingapp.model.dao.ExtraAccountHolderAuthorisationDataDao;
import c16.mpb.bankingapp.model.dao.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
@SessionAttributes("sessionUser")
public class MyPiggyBankController {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    BankAccountDao bankAccountDao;

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    ExtraAccountHolderAuthorisationDataDao extraAccountHolderAuthorisationDataDao;

    @GetMapping(value = "mypiggybank")
    public String myPiggyBankHandler() {

        return "mypiggybank";
    }

    @GetMapping(value = "add_account")
    public String addAccountHandler(Model model) {
        return "add_account";
    }

    @PostMapping(value = "account") 
    public String myPiggyBankFormHandler(HttpSession session, @ModelAttribute BankAccount bankAccount, Model model) {
        BankAccount selectedAccount = bankAccountDao.findByIban(bankAccount.getIban());
        String accounttype = selectedAccount.getAccountType().toString();
        List<Transaction> transactions = selectedAccount.getTransactionHistory();

        Collections.sort(transactions, Collections.reverseOrder());

        model.addAttribute("bankAccount", selectedAccount);
        model.addAttribute("transactions", transactions);
        model.addAttribute("accounttype", accounttype);
        model.containsAttribute("username");
        return "account";
    }

    @GetMapping(value = "enter_authorisation_code")
    public String autorisationCodeHandler(HttpSession session, Model model) {
        model.addAttribute("authorisationdata",new ExtraAccountHolderAuthorisationData());
        User currentUser = (User) session.getAttribute("sessionUser");
        String userName = currentUser.getUsername();
        ExtraAccountHolderAuthorisationData authorisationData = extraAccountHolderAuthorisationDataDao.findByUserName(userName);

        String iban = authorisationData.getIban();

        BankAccount accountOwner = bankAccountDao.findByIban(iban);
        Customer owner = accountOwner.getOwner();
        String ownerUsername= owner.getUsername();

        authorisationData.setVerificationCode(0);
        model.addAttribute("accountOwner",ownerUsername);
        model.addAttribute("authorisationdata",authorisationData);

        return "enter_authorisation_code";}

}
