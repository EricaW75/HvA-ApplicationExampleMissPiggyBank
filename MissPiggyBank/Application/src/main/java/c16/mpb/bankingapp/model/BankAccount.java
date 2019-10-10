package c16.mpb.bankingapp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BankAccount {

    @Id
    @GeneratedValue
    private int id;
    private String iban;
    @ManyToOne
    private Customer owner;
    private double balance;
    private AccountType type;
    @ManyToMany(mappedBy = "bankAccounts")
    private List<Customer> authorized;
    @ManyToMany
    private List<Transaction> transactionHistory;
   
   public BankAccount() {
       this("");
   }

   public BankAccount(String iban) {
       this(0, iban, null, 0.0);
   }

    public BankAccount(int id, String iban, Customer owner, double balance) {
        this(id, iban, owner, balance, null);
    }

    public BankAccount(int id, String iban, Customer owner, double balance, AccountType type) {
        this.id = id;
        this.iban = iban;
        this.owner = owner;
        this.balance = balance;
        this.type = type;
        this.authorized = new ArrayList<Customer>();
        this.transactionHistory = new ArrayList<Transaction>();
    }


    public BankAccount(int id, String iban, Customer owner, double balance, AccountType type, List<Customer> authorized, List<Transaction> transactionHistory) {
        this.id = id;
        this.iban = iban;
        this.owner = owner;
        this.balance = balance;
        this.type = type;
        this.authorized = authorized;
        this.transactionHistory = transactionHistory;
    }

    public void addToTransactionhistory(Transaction transaction) {
        this.transactionHistory.add(transaction);
    }

    public void addAuthorizedUser(Customer authorizedUser) {
        this.authorized.add(authorizedUser);
    }

    public void getMoney(double payment) {
        this.balance += payment;
    }

    public void substractPayment(double payment) {
        this.balance -= payment;
    }

    public AccountType getAccountType() {
        return this.type;
    }

    public void setAccountType(AccountType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIban() {
        return this.iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Customer getOwner() {
        return this.owner;
    }

    public List<Transaction> getTransactionHistory() {
        return this.transactionHistory;
    }
}