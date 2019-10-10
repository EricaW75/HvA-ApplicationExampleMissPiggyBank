package c16.mpb.bankingapp.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c16.mpb.bankingapp.model.PaymentMachine;
import c16.mpb.bankingapp.model.dao.PaymentMachineDao;

@Service
public class PaymentMachineService {

  @Autowired
PaymentMachineDao paymentMachineDao;

  public PaymentMachineService() {
    super();
  }

  public String serialize(PaymentMachine paymentMachine) {
    Gson gson = new Gson();
    String json = gson.toJson(paymentMachine);
    return json;
  }

  public PaymentMachine deserializePaymentMachine(String json) {
    Gson gson = new Gson();
   PaymentMachine paymentMachine = gson.fromJson(json, PaymentMachine.class);
    return paymentMachine;
  }
  
  public String serialize(PinPaymentData paymentData) {
    Gson gson = new Gson();
    String json = gson.toJson(paymentData);
    return json;
  }

  public PinPaymentData deserializePinPaymentData(String json) {
    Gson gson = new Gson();
   PinPaymentData paymentData = gson.fromJson(json, PinPaymentData.class);
    return paymentData;
  }

  public class PinPaymentData {
    private String iban;
    private int pin;
    private double amount;

    private PinPaymentData(){}

    private PinPaymentData(String iban, int pin, double amount){
      this.iban = iban;
      this.pin = pin;
      this.amount = amount;
    }

    public String getIban() {
      return this.iban;
    }

    public int getPin() {
      return this.pin;
    }

    public double getAmount() {
      return this.amount;
    }
    

  }
}
