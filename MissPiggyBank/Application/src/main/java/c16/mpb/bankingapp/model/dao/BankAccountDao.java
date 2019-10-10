package c16.mpb.bankingapp.model.dao;

import c16.mpb.bankingapp.model.AccountType;
import c16.mpb.bankingapp.model.BankAccount;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BankAccountDao extends CrudRepository<BankAccount, Integer> {
    public BankAccount findByIban(String iban);
    public List<BankAccount> findByOwnerId(int ownerId);

    @Query("from BankAccount B where B.type=:AccountType")
    public Iterable<BankAccount> findByAccountType(@Param("AccountType") AccountType accountType);
}
