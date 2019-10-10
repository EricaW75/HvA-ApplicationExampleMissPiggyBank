package c16.mpb.bankingapp.controller;

import c16.mpb.bankingapp.model.*;
import c16.mpb.bankingapp.model.dao.BankAccountDao;
import c16.mpb.bankingapp.model.dao.CustomerDao;
import c16.mpb.bankingapp.model.dao.UserDao;
import c16.mpb.bankingapp.model.dto.TransactionDto;

import c16.mpb.bankingapp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    BankAccountDao bankAccountDao;

    @Autowired
    UserDao userDao;

    @Autowired
    TransactionDto transactionDto;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    LoginService login;

    @PostMapping(value = "make_payment")
    public String makePaymentButtonHandler(@ModelAttribute BankAccount bankAccount, Model model) {
        String selectedAccountIban = bankAccount.getIban();
        BankAccount selectedAccount = bankAccountDao.findByIban(selectedAccountIban);
        model.addAttribute("debitiban", selectedAccountIban);
        model.addAttribute("debitname", selectedAccount.getOwner().getFirstName() + " " + selectedAccount.getOwner().getLastName() );
        model.addAttribute("balance", selectedAccount.getBalance());
        transactionDto.setDebitAccount(selectedAccountIban);
        model.addAttribute("transactionDto", transactionDto);
        return "make_payment";
    }

    @PostMapping(value = "add_account_holder")
    public String addAccountHolderButtonHandler(@ModelAttribute BankAccount bankAccount, Model model) {
        String selectedAccountIban = bankAccount.getIban();
        BankAccount selectedAccount = bankAccountDao.findByIban(selectedAccountIban);

        StringBuilder fullname = new StringBuilder();
        fullname.append(selectedAccount.getOwner().getFirstName() + " ");
        if (selectedAccount.getOwner().getPrefix() != null) {
            fullname.append(selectedAccount.getOwner().getPrefix() + " ");
        }
        fullname.append(selectedAccount.getOwner().getLastName());

        ExtraAccountHolderAuthorisationData data = new ExtraAccountHolderAuthorisationData();
        data.setIban(selectedAccountIban);

        model.addAttribute("addAccountHolderAuthorisationData", data);
        model.addAttribute("fullname", fullname);
        return "add_account_holder";
    }

//    @PostMapping(value = "backtomypiggybank")
//    public String backbuttonHandler(HttpSession session, Model model) {
//        User sessionUser = (User) session.getAttribute("sessionUser");
//        Customer owner = customerDao.findByUsername(sessionUser.getUsername());
//
//        List<BankAccount> bankAccounts = new ArrayList<>();
//        bankAccounts = owner.getBankAccounts();
//        BankAccount selectedAccount = bankAccounts.get(0);
//
//        StringBuilder fullname = new StringBuilder();
//        fullname.append(owner.getFirstName() + " ");
//        if (owner.getPrefix() != null) {fullname.append(owner.getPrefix() + " ");
//        }
//        fullname.append(owner.getLastName());
//
//        model.addAttribute("bankAccounts", bankAccounts);
//        model.addAttribute("fullname", fullname);
//        model.addAttribute("selectedAccount", selectedAccount);
//        //Erica
//
//        model.addAttribute("email", selectedAccount.getOwner().getEmailaddress());
//        model.addAttribute("streetName", selectedAccount.getOwner().getStreetName());
//        model.addAttribute("number", selectedAccount.getOwner().getNumber());
//        model.addAttribute("numberSuffix", selectedAccount.getOwner().getNumberSuffix());
//        model.addAttribute("city", selectedAccount.getOwner().getCity());
//        model.addAttribute("postalCode", selectedAccount.getOwner().getPostalCode());
//        model.addAttribute("phonenumber", selectedAccount.getOwner().getPhonenumber());
//        model.addAttribute("bsn", selectedAccount.getOwner().getBsn());
//
//
//        return "mypiggybank";
//    }

    @PostMapping(value = "backtomypiggybank")
    public String backbuttonHandler(HttpSession session, Model model) {
        String sessionUserName = (String) session.getAttribute("sessionUserName");
        Customer owner = customerDao.findByUsername(sessionUserName);

        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts = owner.getBankAccounts();
        BankAccount selectedAccount = bankAccounts.get(0);

        StringBuilder fullname = new StringBuilder();
        fullname.append(owner.getFirstName() + " ");
        if (owner.getPrefix() != null) {fullname.append(owner.getPrefix() + " ");
        }
        fullname.append(owner.getLastName());

        model.addAttribute("bankAccounts", bankAccounts);
        model.addAttribute("fullname", fullname);
        model.addAttribute("selectedAccount", selectedAccount);

        //Erica - addition to display business customer
        boolean isBusinessCustomer = login.isBusinessCustomer(sessionUserName);
        model.addAttribute("isBusiness", isBusinessCustomer);
        int dbUserId = owner.getUserId();

        if (isBusinessCustomer) {

            model.addAttribute("businessname", login.getBusinessName(dbUserId));
            model.addAttribute("kvkNummer", login.getBusinessKvk(dbUserId));
            model.addAttribute("sector", login.getSector(dbUserId));
            model.addAttribute("accountMan", login.getAccountMan(dbUserId));
        }

        //Erica addition to display customer
        model.addAttribute("email", selectedAccount.getOwner().getEmailaddress());
        model.addAttribute("streetName", selectedAccount.getOwner().getStreetName());
        model.addAttribute("number", selectedAccount.getOwner().getNumber());
        model.addAttribute("numberSuffix", selectedAccount.getOwner().getNumberSuffix());
        model.addAttribute("city", selectedAccount.getOwner().getCity());
        model.addAttribute("postalCode", selectedAccount.getOwner().getPostalCode());
        model.addAttribute("phonenumber", selectedAccount.getOwner().getPhonenumber());
        model.addAttribute("bsn", selectedAccount.getOwner().getBsn());

        return "mypiggybank";

    }

}
