package c16.mpb.bankingapp.model.dao;

import c16.mpb.bankingapp.model.Transaction;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TransactionDao extends CrudRepository<Transaction, Integer> {
    public List<Transaction> findByCreditAccountId(int creditAccountId);
    public List<Transaction> findByDebitAccountId(int debitAccountId);
}
