package c16.mpb.bankingapp.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c16.mpb.bankingapp.model.BankAccount;
import c16.mpb.bankingapp.model.Customer;
import c16.mpb.bankingapp.model.ExtraAccountHolderAuthorisationData;
import c16.mpb.bankingapp.model.User;
import c16.mpb.bankingapp.model.dao.BankAccountDao;
import c16.mpb.bankingapp.model.dao.CustomerDao;
import c16.mpb.bankingapp.model.dao.ExtraAccountHolderAuthorisationDataDao;

@Service
public class AuthorizeUser {

    @Autowired 
    BankAccountDao bankAccountDao;

    @Autowired 
    CustomerDao customerDao;

    @Autowired
    ExtraAccountHolderAuthorisationDataDao extraAccountHolderAuthorisationDataDao;

    public AuthorizeUser () {
    }

    public boolean validateCode( ExtraAccountHolderAuthorisationData data) {
        ExtraAccountHolderAuthorisationData databaseData = extraAccountHolderAuthorisationDataDao.findById(data.getId()).get();
        int verificationCode = data.getVerificationCode();
        return (databaseData.getVerificationCode() == verificationCode);
    }

    public void processAuthorization(ExtraAccountHolderAuthorisationData data, HttpSession session) {
        BankAccount bankAccount = bankAccountDao.findByIban(data.getIban());
        String username = ((User)(session.getAttribute("sessionUser"))).getUsername();
        Customer authorizedCustomer = customerDao.findByUsername(username);

        bankAccount.addAuthorizedUser(authorizedCustomer);
        bankAccountDao.save(bankAccount);
        authorizedCustomer.addBankAccount(bankAccount);
        customerDao.save(authorizedCustomer);
        extraAccountHolderAuthorisationDataDao.delete(data);
    }
}