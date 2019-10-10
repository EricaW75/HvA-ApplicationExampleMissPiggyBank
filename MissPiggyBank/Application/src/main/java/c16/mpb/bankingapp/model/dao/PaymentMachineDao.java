package c16.mpb.bankingapp.model.dao;

import org.springframework.data.repository.CrudRepository;

import c16.mpb.bankingapp.model.PaymentMachine;

public interface PaymentMachineDao extends CrudRepository<PaymentMachine, Integer> {
    public PaymentMachine findByIban(String iban);
    public PaymentMachine findByControlNumber(int controlNumber);
}
