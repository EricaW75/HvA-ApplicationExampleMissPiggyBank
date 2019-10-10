package c16.mpb.bankingapp.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "businessCustomerId")
public class BusinessCustomer extends Customer{

    private String companyName;
    private int kvkNumber;
    private Sector sector;
    private AccountMan accountMan;


    public BusinessCustomer(){
        super();
    }

    public BusinessCustomer(String username, String password, int id, String role, String emailadress, String firstName, String prefix, String lastName, int bsn, String streetName, int number, String numberSuffix, String city, String postalCode, String phonenumber, List<BankAccount> bankAccounts, String companyName, int kvkNumber, String sector, String accountMan) {
        super(username, password, id, role, emailadress, firstName, prefix, lastName, bsn, streetName, number, numberSuffix, city, postalCode, phonenumber);
        this.companyName = companyName;
        this.kvkNumber = kvkNumber;
        this.sector = Sector.valueOf(sector);
        this.accountMan = AccountMan.valueOf(accountMan);
    }

    public BusinessCustomer(String username, String password, int id, String role, String emailadress, String firstName, String prefix, String lastName, int bsn, String streetName, int number, String numberSuffix, String city, String postalCode, String phonenumber, String companyName, int kvkNumber, Sector sector, AccountMan accountMan) {
        super(username, password, id, role, emailadress, firstName, prefix, lastName, bsn, streetName, number, numberSuffix, city, postalCode, phonenumber);
        this.companyName = companyName;
        this.kvkNumber = kvkNumber;
        this.sector = sector;
        this.accountMan = accountMan;
    }


    public AccountMan getAccountMan() {
        return accountMan;
    }

   public void setAccountMan(AccountMan accountMan) {this.accountMan = accountMan; }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getKvkNumber() {
        return kvkNumber;
    }

    public void setKvkNumber(int kvkNumber) {
        this.kvkNumber = kvkNumber;
    }

    public Sector getSector() {return this.sector;}

    public void setSector(Sector sector) {this.sector = sector;}
}
