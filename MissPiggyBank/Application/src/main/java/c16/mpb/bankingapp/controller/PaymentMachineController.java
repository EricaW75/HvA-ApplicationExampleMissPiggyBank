package c16.mpb.bankingapp.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import c16.mpb.bankingapp.model.BankAccount;
import c16.mpb.bankingapp.model.PaymentMachine;
import c16.mpb.bankingapp.model.Transaction;
import c16.mpb.bankingapp.model.dao.BankAccountDao;
import c16.mpb.bankingapp.model.dao.PaymentMachineDao;
import c16.mpb.bankingapp.service.PaymentAuthorizer;
import c16.mpb.bankingapp.service.PaymentMachineService;
import c16.mpb.bankingapp.service.PaymentAuthorizer.IbanError;
import c16.mpb.bankingapp.service.PaymentAuthorizer.InsufficientFundsError;
import c16.mpb.bankingapp.service.PaymentMachineService.PinPaymentData;

@RestController
public class PaymentMachineController {

@Autowired
PaymentMachineService paymentMachineService;

@Autowired
PaymentMachineDao paymentMachineDao;

@Autowired
BankAccountDao bankAccountDao;

@Autowired
PaymentAuthorizer paymentAuthorizer;

  @PostMapping(value = "paymentmachine/connect/new")
  public String registerMachine(@RequestBody String json) {
      PaymentMachine p = paymentMachineService.deserializePaymentMachine(json);
      System.out.println(p.toString());
      paymentMachineDao.save(p);
      return "Request received";
  }

  @GetMapping(value = "paymentmachine/connect/{controlNumber}")
  public String getMember(@PathVariable int controlNumber) {
    PaymentMachine p = paymentMachineDao.findByControlNumber(controlNumber);
    String json = paymentMachineService.serialize(p);
    System.out.println(json);
    return json;
  }

  @PostMapping(value = "paymentmachine/payment/{id}")
  public String processPayment(@RequestBody String json, @PathVariable int id) {
    String result = "";
      PinPaymentData pd = paymentMachineService.deserializePinPaymentData(json);
      PaymentMachine pm = paymentMachineDao.findById(id).get();
      
      BankAccount creditAccount = bankAccountDao.findByIban(pm.getIban());
      BankAccount debitAccount = bankAccountDao.findByIban(pd.getIban());
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      String timeStamp = dtf.format(LocalDateTime.now());

      Transaction pinTransaction = new Transaction(0, debitAccount, creditAccount, pd.getAmount(), timeStamp, "pinbetaling");
      
      try {
        paymentAuthorizer.checkValidity(pinTransaction);
        paymentAuthorizer.execute(pinTransaction);
        result = "Success";
      } catch (InsufficientFundsError e) {
        result = "Insufficient funds error";
      }
      catch (IbanError e) {
        result = "IBAN error";
      }
      return result;
    }
  }
