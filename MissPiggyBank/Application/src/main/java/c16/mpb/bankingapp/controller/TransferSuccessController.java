package c16.mpb.bankingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import c16.mpb.bankingapp.model.dto.TransactionDto;

@Controller
public class TransferSuccessController {
    @GetMapping(value = "transfer_success")
    public String transferSuccessHandler(@ModelAttribute TransactionDto transactionDto, Model model){
        model.addAttribute("transactionDto", transactionDto);
        return "transfer_success";
    }
}