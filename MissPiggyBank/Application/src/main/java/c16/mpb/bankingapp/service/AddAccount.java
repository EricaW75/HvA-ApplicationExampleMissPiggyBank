package c16.mpb.bankingapp.service;

import c16.mpb.bankingapp.model.AccountType;
import c16.mpb.bankingapp.model.BankAccount;
import c16.mpb.bankingapp.model.BusinessCustomer;
import c16.mpb.bankingapp.model.Customer;
import c16.mpb.bankingapp.model.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AddAccount {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    BusinessCustomerDao businessCustomerDao;

    @Autowired
    BankAccountDao bankAccountDao;

    final String LANDCODE = "NL";
    final String BANKCODE = "MPBA";
    final String ACCOUNT_NR_FIXED = "001974"; //1974 = first tv-appearance Miss Piggy
    public final String MODULO = "97";

    public AddAccount() {
        super();
    }

    public void makeNewAccount(Customer customer){
        customerDao.save(customer);
        String newIban = createIban();
        BankAccount newAccount = new BankAccount(0, newIban, customer, 0.00);
        if (customer instanceof BusinessCustomer) {
            newAccount.setAccountType(AccountType.BUSINESS);
        } else {
            newAccount.setAccountType(AccountType.CURRENT);}
        bankAccountDao.save(newAccount);
        customer.addBankAccount(newAccount);
        customerDao.save(customer);
    }

    public String createIban() {
        String accountNrVar = createAccountNrVar();
        String controlNumber = calculateControlNumber(accountNrVar);
        String iban = LANDCODE + controlNumber + BANKCODE + ACCOUNT_NR_FIXED + accountNrVar;
        return iban;
    }

    public String createAccountNrVar(){
        ArrayList<BankAccount> bankAccountList = (ArrayList<BankAccount>) bankAccountDao.findAll();
        List<String> ibanList = bankAccountList.stream().map(BankAccount::getIban).collect(Collectors.toList());

        ArrayList<String> lastFourDigitsList = new ArrayList<>();
        for(String item:ibanList){
            String varPartNr = item.substring(14, 18);
            lastFourDigitsList.add(varPartNr);
        }
        Collections.sort(lastFourDigitsList);

        String lastFourDigits;
        if (ibanList.size() == 0) {
            lastFourDigits = "1001";
        } else {
            String highestIbanInUse = lastFourDigitsList.get(lastFourDigitsList.size()-1);
            int plusOne = Integer.parseInt(highestIbanInUse) + 1;
            lastFourDigits = String.valueOf(plusOne);
        }
        return lastFourDigits;
    }


    public  String calculateControlNumber(String accountNrVar) {
        String convertedBankCode = convertCodeToNumber(BANKCODE);
        String convertedLandCode = convertCodeToNumber(LANDCODE);
        String identifierAsNumberString = convertedBankCode + ACCOUNT_NR_FIXED + accountNrVar + convertedLandCode + "00";
        BigInteger identifierAsNumber = new BigInteger(identifierAsNumberString);
        BigInteger remainder = identifierAsNumber.remainder(new BigInteger(MODULO));

        //het controlegetal is 98 min deze rest, als het controlegetal kleiner dan 10 is, een voorloopnul toevoegen.
        int tempControlNumber = 98 - remainder.intValue();
        String controlNumber = (tempControlNumber < 10 ? "0" : "") + tempControlNumber;
        return controlNumber;
    }

    public String convertCodeToNumber(String codeSnippet) {
        String result = "";
        // komt uit officiele IBAN berekening handleiding
        for (int i = 0; i < codeSnippet.length(); i++) {
            result += (java.lang.Character.toLowerCase(codeSnippet.charAt(i))) - 'a' + 10;
        }
        return result;
    }
}
