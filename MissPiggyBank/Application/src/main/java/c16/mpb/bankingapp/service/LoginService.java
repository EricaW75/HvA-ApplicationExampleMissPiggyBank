package c16.mpb.bankingapp.service;

import c16.mpb.bankingapp.model.BankAccount;
import c16.mpb.bankingapp.model.BusinessCustomer;
import c16.mpb.bankingapp.model.Sector;
import c16.mpb.bankingapp.model.ExtraAccountHolderAuthorisationData;
import c16.mpb.bankingapp.model.User;
import c16.mpb.bankingapp.model.dao.BankAccountDao;
import c16.mpb.bankingapp.model.dao.CustomerDao;
import c16.mpb.bankingapp.model.dao.DepartmentHeadDao;
import c16.mpb.bankingapp.model.dao.ExtraAccountHolderAuthorisationDataDao;
import c16.mpb.bankingapp.model.dao.UserDao;
import c16.mpb.bankingapp.model.dao.*;
import c16.mpb.bankingapp.model.dto.BusinessCustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    UserDao userDao;

    @Autowired
    BankAccountDao bankAccountDao;

    @Autowired
    DepartmentHeadDao departmentHeadDao;

    @Autowired
    ExtraAccountHolderAuthorisationDataDao extraAccountHolderAuthorisationDataDao;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    BusinessCustomerDao businessCustomerDao;

    public LoginService() {
        super();
    }

    public User loginUser(User user) {
        if (!validatePassword(user)) {
            return null;
        }
        return userDao.findByUsername(user.getUsername()).get(0);
    }

    public boolean validatePassword(User user) {
        List<User> dbUsers = userDao.findByUsername(user.getUsername());
        if (dbUsers.size() != 0 && dbUsers.get(0).getPassword().equals(user.getPassword())) {
            return true;
        } else return false;
    }

    public String getDepartmentHeadPosition(User user) {
        return departmentHeadDao.findById(user.getUserId()).getDepartment();
    }

    public List<BankAccount> getUserBankAccounts(User user) {
        List<BankAccount> customerAccounts = customerDao.findByUsername(user.getUsername()).getBankAccounts();
        return customerAccounts;
    }

    public boolean checkForPendingAuthorization(String userName) {
        if (extraAccountHolderAuthorisationDataDao.findByUserName(userName) != null) {
            return true;
        } else return false;
    }


    //Erica - Add business customer to MyPiggyBank page
public boolean isBusinessCustomer(String userName) {
    BusinessCustomer bc = businessCustomerDao.findByUsername(userName);
    if (bc == null) {
        return false;
    } else {
        return true;
    }
}



public String getBusinessName(int userId) {
    String businessname;
    BusinessCustomer bc = businessCustomerDao.findById(userId).get();
    businessname = bc.getCompanyName();
    return businessname;

}

    public int getBusinessKvk(int userId) {
        int kvkNummer;
        BusinessCustomer bc = businessCustomerDao.findById(userId).get();
        kvkNummer = bc.getKvkNumber();
        return kvkNummer;

    }

    public String getSector(int userId) {
        String sector;
        BusinessCustomer bc = businessCustomerDao.findById(userId).get();
        sector = (bc.getSector().toString());
        return sector;

    }

    public String getAccountMan(int userId) {
        String accountMan;
        BusinessCustomer bc = businessCustomerDao.findById(userId).get();
        accountMan = (bc.getAccountMan().toString());
        return accountMan;

    }
}

