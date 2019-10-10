package c16.mpb.bankingapp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.ArrayList;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "customerId")
public class Customer extends User {

    private String firstName;
    private String prefix;
    private String lastName;
    private int bsn;
    private String streetName;
    private int number;
    private String numberSuffix;
    private String city;
    private String postalCode;
    private String phonenumber;
    @ManyToMany
    private List<BankAccount> bankAccounts;

    public Customer() {
        super();
        this.bankAccounts = new ArrayList<>();
    }

    public Customer(String username, String password, int id, String role, String emailadress, String firstName, String prefix, String lastName, int bsn, String streetName, int number, String numberSuffix, String city, String postalCode, String phonenumber) {
        super(username, password, id, role, emailadress);
        this.firstName = firstName;
        this.prefix = prefix;
        this.lastName = lastName;
        this.bsn = bsn;
        this.streetName = streetName;
        this.number = number;
        this.numberSuffix = numberSuffix;
        this.city = city;
        this.postalCode = postalCode;
        this.phonenumber = phonenumber;
        this.bankAccounts = new ArrayList<>();
    }

    public Customer(String username, String password, int id, String role, String emailadress, String firstName, String prefix, String lastName, int bsn, String streetName, int number, String numberSuffix, String city, String postalCode, String phonenumber, List<BankAccount> bankAccounts) {
        super(username, password, id, role, emailadress);
        this.firstName = firstName;
        this.prefix = prefix;
        this.lastName = lastName;
        this.bsn = bsn;
        this.streetName = streetName;
        this.number = number;
        this.numberSuffix = numberSuffix;
        this.city = city;
        this.postalCode = postalCode;
        this.phonenumber = phonenumber;
        this.bankAccounts = bankAccounts;
    }

    public boolean checkLengthBsn(int bsn) {
        int length = String.valueOf(bsn).length();
        if(length == 9) {
            return true;
        }
        return false;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBsn() {
        return bsn;
    }

    public void setBsn(int bsn) {
        this.bsn = bsn;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getNumberSuffix() {
        return numberSuffix;
    }

    public void setNumberSuffix(String numberSuffix) {
        this.numberSuffix = numberSuffix;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public void addBankAccount(BankAccount bankAccount) {
        this.bankAccounts.add(bankAccount);
    }

}