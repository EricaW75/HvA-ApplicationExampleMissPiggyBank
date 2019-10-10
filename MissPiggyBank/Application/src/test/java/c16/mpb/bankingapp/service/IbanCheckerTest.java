package c16.mpb.bankingapp.service;

import c16.mpb.bankingapp.model.BankAccount;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;


public class IbanCheckerTest {

    @Autowired
    IbanChecker ibanChecker;

    public static final String IBAN_GABOR = "NL56RABO0315730846";
    public static final String IBAN_INGE = "NL61INGB0008726594";
    public static final String IBAN_GENERATED = "NL38MPBA0019740624";
    public static final String IBAN_FALSE = "NL38MPBA0019740622";

    @Test
    public void checkIbanCheckerCorrect() {

        //voer iban in waarvan je zeker weet dat die goed is.
        BankAccount testBankAccount = new BankAccount(IBAN_GENERATED);

        // Maak nieuwe ibanchecker
        ibanChecker = new IbanChecker();

        //loop hem door de IbanChecker
        boolean isCorrect = ibanChecker.isIbanCorrect(testBankAccount);

        //als uitkomst true is dan is test geslaagd.
        assertEquals(true, isCorrect);

    }

    @Test
    public void checkIbanCheckerFalse() {

        //voer iban in waarvan je zeker weet dat die NIET goed is.
        BankAccount testBankAccount = new BankAccount(IBAN_FALSE);

        // Maak nieuwe ibanchecker
        ibanChecker = new IbanChecker();

        //loop hem door de IbanChecker
        boolean isFalse = ibanChecker.isIbanCorrect(testBankAccount);

        //als uitkomst false is dan is test geslaagd.
        assertEquals(false, isFalse);

    }


}
