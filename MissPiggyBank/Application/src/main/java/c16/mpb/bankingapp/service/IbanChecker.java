package c16.mpb.bankingapp.service;

import c16.mpb.bankingapp.model.BankAccount;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class IbanChecker {

    public IbanChecker() {
        super();
    }


    public boolean isIbanCorrect(BankAccount bankAccount) {
        String ibanToCheck = bankAccount.getIban();
        String[] ibanDeconstruct = pullApartIban(ibanToCheck);
        String convertedBankCode = convertCodeToNumber(ibanDeconstruct[2]);
        String convertedLandCode = convertCodeToNumber(ibanDeconstruct[0]);

        String ibanAsString = convertedBankCode + ibanDeconstruct[3] + convertedLandCode + ibanDeconstruct[1];
        int remainderInteger = calculateRemainder(ibanAsString);

        int controlNumber = Integer.parseInt(ibanDeconstruct[1]);

        if (remainderInteger == 1 && checkControlNumber(controlNumber)) {
            return true;
        } else {
            return false;
        }
    }


    public static String[] pullApartIban(String ibanToCheck) {
        String[] ibanDeconstruct = new String[4];
        ibanDeconstruct[0] = ibanToCheck.substring(0, 2); //landcode
        ibanDeconstruct[1] = ibanToCheck.substring(2, 4); //controlNumberString
        ibanDeconstruct[2] = ibanToCheck.substring(4, 8); //bankCode
        ibanDeconstruct[3] = ibanToCheck.substring(8, 18); //accountNumber

        return ibanDeconstruct;
    }


    public static String convertCodeToNumber(String codeSnippet) {
        String result = "";
        for (int i = 0; i < codeSnippet.length(); i++) {
            result += (java.lang.Character.toLowerCase(codeSnippet.charAt(i))) - 'a' + 10;
        }
        return result;
    }


    public static int calculateRemainder(String ibanAsString) {
        BigInteger ibanAsNumber = new BigInteger(ibanAsString);
        BigInteger remainder = ibanAsNumber.remainder(new BigInteger("97"));
        int remainderInteger = remainder.intValue();

        return remainderInteger;
    }


    public static boolean checkControlNumber(int controlNumber) {
        if (controlNumber >= 2 && controlNumber <= 98) {
            return true;
        } else
            return false;
    }

}
