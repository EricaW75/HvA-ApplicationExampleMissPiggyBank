package c16.mpb.bankingapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PaymentMachine {
    
    @Id
    @GeneratedValue
    int id;
    String iban;
    int controlNumber;

    public PaymentMachine() {
        super();
    }

    public PaymentMachine(int id, String iban, int controlNumber) {
        this.id = id;
        this.iban = iban;
        this.controlNumber = controlNumber;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getControlNumber() {
        return this.controlNumber;
    }

    public void setControlNumber(int controlNumber) {
        this.controlNumber = controlNumber;
    }

    public String getIban() {
        return this.iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", controlNumber='" + getControlNumber() + "'" +
            ", iban='" + getIban() + "'" +
            "}";
    }
    
}