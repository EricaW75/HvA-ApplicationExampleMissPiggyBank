package c16.mpb.bankingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransactionFailedController {

    @GetMapping(value = "transaction_failed")
    public String transactionFailedHandler(){
        return "transaction_failed";
    }
}