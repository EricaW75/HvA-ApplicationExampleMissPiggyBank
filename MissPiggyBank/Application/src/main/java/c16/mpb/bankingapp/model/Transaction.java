package c16.mpb.bankingapp.model;

import javax.persistence.*;

@Entity
public class Transaction implements Comparable<Transaction> {

    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private BankAccount debitAccount;
    @OneToOne
    private BankAccount creditAccount;
    private double paymentAmount;
    private String timeStamp;
    private String description;


    public Transaction() {
       this(0, null, null, 0.0, "", "");
    }

    public Transaction(int id, BankAccount debitAccount, double payment) {
       this(id, debitAccount, null, payment, "", "");
    }

    public Transaction(int id, BankAccount debitAccount, BankAccount creditAccount, double paymentAmount) {
        this(id, debitAccount, creditAccount, paymentAmount, "", "");
    }

    public Transaction(int id, BankAccount debitAccount, BankAccount creditAccount, double paymentAmount, String timeStamp) {
        this(id, debitAccount, creditAccount, paymentAmount, timeStamp, "");
    }
    
    public Transaction(int id, BankAccount debitAccount, BankAccount creditAccount, double paymentAmount, String timeStamp, String description) {
        this.id = id;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.paymentAmount = paymentAmount;
        this.timeStamp = timeStamp;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BankAccount getDebitAccount() {
        return this.debitAccount;
    }

    public void setDebitAccount(BankAccount debitAccount) {
        this.debitAccount = debitAccount;
    }

    public BankAccount getCreditAccount() {
        return this.creditAccount;
    }

    public void setCreditAccount(BankAccount creditAccount) {
        this.debitAccount = creditAccount;
    }

    public double getPaymentAmount() {
        return this.paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public int compareTo(Transaction other) {
        return this.timeStamp.compareTo(other.getTimeStamp());
    }

}