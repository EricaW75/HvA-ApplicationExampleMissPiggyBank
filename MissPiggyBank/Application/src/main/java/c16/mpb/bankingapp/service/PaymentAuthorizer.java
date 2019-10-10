package c16.mpb.bankingapp.service;

import c16.mpb.bankingapp.model.BankAccount;
import c16.mpb.bankingapp.model.Transaction;
import c16.mpb.bankingapp.model.dao.BankAccountDao;
import c16.mpb.bankingapp.model.dao.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentAuthorizer {

    @Autowired
    IbanChecker ibanChecker;

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    BankAccountDao bankAccountDao;

    public PaymentAuthorizer() {
        super();
    }

    public boolean checkValidity(Transaction transaction) throws InsufficientFundsError, IbanError{
        if (!hasSufficientFunds(transaction.getPaymentAmount(), transaction.getDebitAccount())) {
            throw new InsufficientFundsError("Onvoldoende saldo om deze transactie te maken.");
        }
        if (!ibanChecker.isIbanCorrect(transaction.getDebitAccount()) 
        || !ibanChecker.isIbanCorrect(transaction.getCreditAccount())) {
            throw new IbanError("De tranasctie is helaas mislukt vanwege een ongeldige IBAN. Controleer deze en probeer opnieuw.");
        }
        return true;
    }

    public synchronized boolean hasSufficientFunds(double payment, BankAccount bankAccount) {
        return bankAccount.getBalance() >= payment;
    }
    
    public synchronized void execute(Transaction transaction) {
            transaction.getDebitAccount().substractPayment(transaction.getPaymentAmount());   
            transaction.getCreditAccount().getMoney(transaction.getPaymentAmount());  
            transaction.getCreditAccount().addToTransactionhistory(transaction);
            transaction.getDebitAccount().addToTransactionhistory(transaction);
            transactionDao.save(transaction);
            bankAccountDao.save(transaction.getDebitAccount());
            bankAccountDao.save(transaction.getCreditAccount());
    }
    
    @SuppressWarnings("serial")
    public class InsufficientFundsError extends Exception  {
        InsufficientFundsError(String s) {
            super(s);
        }
    }

    @SuppressWarnings("serial")
    public class IbanError extends Exception {
        IbanError(String s) {
            super(s);
        }
    }
}
