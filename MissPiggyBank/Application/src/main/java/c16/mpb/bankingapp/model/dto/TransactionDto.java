package c16.mpb.bankingapp.model.dto;

import org.springframework.stereotype.Component;

@Component
public class TransactionDto {
    private int id;
    private String debitAccount;
    private String creditAccount;
    private double paymentAmount;
    private String timeStamp;
    private String description;


    public TransactionDto() {
    }

    public TransactionDto( int id, String debitAccount, String creditAccount, double paymentAmount, String timeStamp, String description){
        this.id = id;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.paymentAmount = paymentAmount;
        this.timeStamp = timeStamp;
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDebitAccount() {
        return this.debitAccount;
    }

    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }

    public String getCreditAccount() {
        return this.creditAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    public double getPaymentAmount() {
        return this.paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}