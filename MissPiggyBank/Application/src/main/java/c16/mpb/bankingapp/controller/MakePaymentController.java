package c16.mpb.bankingapp.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import c16.mpb.bankingapp.model.User;
import c16.mpb.bankingapp.model.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import c16.mpb.bankingapp.model.BankAccount;
import c16.mpb.bankingapp.model.Transaction;
import c16.mpb.bankingapp.model.dao.BankAccountDao;
import c16.mpb.bankingapp.model.dao.TransactionDao;
import c16.mpb.bankingapp.model.dto.TransactionDto;
import c16.mpb.bankingapp.service.PaymentAuthorizer;
import c16.mpb.bankingapp.service.PaymentAuthorizer.IbanError;
import c16.mpb.bankingapp.service.PaymentAuthorizer.InsufficientFundsError;


@Controller
@SessionAttributes("sessio nUser")
public class MakePaymentController {

    @Autowired 
    TransactionDto transactionDto;

    @Autowired
    BankAccountDao bankAccountDao;

    @Autowired 
    TransactionDao transactionDao;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    PaymentAuthorizer paymentAuthorizer;
    
    @GetMapping(value = "make_payment")
    public String menuPaymentHandler(Model model){
        return "make_payment";
    }
    
    @PostMapping(value = "paymentHandler")
    public String paymentHandler(@ModelAttribute TransactionDto transactionDto, Model model) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        transactionDto.setTimeStamp(dtf.format(LocalDateTime.now()));
        BankAccount trueDebitAccount = bankAccountDao.findByIban(transactionDto.getDebitAccount());
        BankAccount trueCreditAccount = bankAccountDao.findByIban(transactionDto.getCreditAccount());
        if (trueCreditAccount == null) {
            model.addAttribute("status", "Incorrect rekeningnummer ingevoerd. Probeer opnieuw. ");
            return "transaction_failed";
        }
        Transaction transaction = new Transaction(0, trueDebitAccount, trueCreditAccount, transactionDto.getPaymentAmount(), transactionDto.getTimeStamp(), transactionDto.getDescription());
        try {
            paymentAuthorizer.checkValidity(transaction);
            paymentAuthorizer.execute(transaction);
            model.addAttribute("transactionDto", transactionDto);
        return "transfer_success";
        } catch (InsufficientFundsError e) {
            model.addAttribute("status", e.getMessage());
            return "transaction_failed";
        } catch (IbanError e){
            model.addAttribute("status", e.getMessage());
            return "transaction_failed";
        }
    }

    @PostMapping(value = "backToAccount")
    public String backButtonToAccountHandler(HttpSession session, Model model) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BankAccount currentBankAccount = bankAccountDao.findByOwnerId(customerDao.findByUsername(sessionUser.getUsername()).getUserId()).get(0);
        List<Transaction> transactions = currentBankAccount.getTransactionHistory();
        Collections.sort(transactions, Collections.reverseOrder());
        model.addAttribute("bankAccount", currentBankAccount);
        model.addAttribute("transactions", transactions);
        model.containsAttribute("username");
        return "account";
    }
}
