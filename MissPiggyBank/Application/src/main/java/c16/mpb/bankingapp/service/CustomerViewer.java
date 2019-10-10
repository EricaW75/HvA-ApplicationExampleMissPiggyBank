package c16.mpb.bankingapp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import c16.mpb.bankingapp.model.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c16.mpb.bankingapp.model.AccountType;
import c16.mpb.bankingapp.model.BankAccount;
import c16.mpb.bankingapp.model.BusinessCustomer;
import c16.mpb.bankingapp.model.Customer;
import c16.mpb.bankingapp.model.Sector;
import c16.mpb.bankingapp.model.dao.BankAccountDao;

@Service
public class CustomerViewer {

    @Autowired
    BankAccountDao bankAccountDao;

    @Autowired
    CustomerDao customerDao;

    BankAccountBalanceSorter balanceSorter = new BankAccountBalanceSorter();
    BankAccountActivitySorter activitySorter = new BankAccountActivitySorter();


    public ArrayList<Customer> getMostActiveBusinessCustomers(int number) {
        ArrayList<Customer> topCustomers = new ArrayList<>();
        ArrayList<BankAccount> businessAccounts = (ArrayList<BankAccount>) bankAccountDao.findByAccountType(AccountType.BUSINESS);
        Collections.sort(businessAccounts, Collections.reverseOrder(activitySorter));
        for (int i = 0; i < number; i++) {
            topCustomers.add(businessAccounts.get(i).getOwner());
        }
        return topCustomers;
    }

   public double getTotalBalanceOfCustomer(Customer customer) {
        ArrayList<BankAccount> customerAccounts = (ArrayList<BankAccount>) bankAccountDao.findByOwnerId(customer.getUserId())
        .stream().collect(Collectors.toList());
        double totalBalance = 0.00;
        for (BankAccount b : customerAccounts) {
            totalBalance += b.getBalance();
        }
        return totalBalance;
   }

    // Nathan - haal lijst van x aantal top accounts op
    public ArrayList<BankAccount> getTopBankAccounts(int aantal) {
        ArrayList<BankAccount> bankAccounts = (ArrayList<BankAccount>) bankAccountDao.findAll();
        Collections.sort(bankAccounts, Collections.reverseOrder(balanceSorter));
        ArrayList<BankAccount> topBankAccounts = new ArrayList<>();
        for (int i = 0; i < aantal; i++) {
            topBankAccounts.add(bankAccounts.get(i));
        }
        return topBankAccounts;
    }
    
    public ArrayList<Customer> getTopBusinessAccounts(int aantal) {
        ArrayList<BankAccount> bankAccounts = (ArrayList<BankAccount>) bankAccountDao.findByAccountType(AccountType.BUSINESS);
        Collections.sort(bankAccounts, Collections.reverseOrder(balanceSorter));
        ArrayList<Customer> topCustomers = new ArrayList<>();
        for (int i = 0; i < aantal; i++) {
            topCustomers.add(bankAccounts.get(i).getOwner());
        }
        return topCustomers;
    }

    public double[] getTotalAccountBalance (List<Customer> customers) {
        double[] accountBalances = new double[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            accountBalances[i] = bankAccountDao.findByOwnerId(customers.get(i).getUserId()).get(0).getBalance();
        }
        return accountBalances;
    }

    public double[] getAverageBalancePerSector() {
        ArrayList<BankAccount> businessAccounts = (ArrayList<BankAccount>) bankAccountDao.findByAccountType(AccountType.BUSINESS);
        double[] result = new double[Sector.values().length];
        int counter = 0;
        for (Sector s : Sector.values()) {
            double balance = 0.0;
            ArrayList<BankAccount> accountsInSector = (ArrayList<BankAccount>) filterBySector(businessAccounts, s);
            for (BankAccount a : accountsInSector) {
                balance += a.getBalance();
            }
            double sectorAverage = balance / accountsInSector.size();
            result[counter++] = sectorAverage;
        }
        return result;
    }

    public List<BankAccount> filterBySector(ArrayList<BankAccount> businessAccounts, Sector sector) {
        List<BankAccount> accountsInSector = businessAccounts
                .stream().filter(b -> ((BusinessCustomer) b.getOwner()).getSector() == sector)
                .collect(Collectors.toList());
        return accountsInSector;
    }

    private class BankAccountBalanceSorter implements Comparator<BankAccount> {
        @Override
        public int compare(BankAccount b1, BankAccount b2) {
            if (b1.getBalance() == b2.getBalance()) {
                return 0;
            } else if (b1.getBalance() > b2.getBalance()) {
                return 1;
            } else return -1;
        }
    }

    private class BankAccountActivitySorter implements Comparator<BankAccount> {
        @Override
        public int compare(BankAccount b1, BankAccount b2) {
            if (b1.getTransactionHistory().size() == b2.getTransactionHistory().size()) {
                return 0;
            } else if (b1.getTransactionHistory().size() > b2.getTransactionHistory().size()) {
                return 1;
            } else return -1;
        }
    }
}