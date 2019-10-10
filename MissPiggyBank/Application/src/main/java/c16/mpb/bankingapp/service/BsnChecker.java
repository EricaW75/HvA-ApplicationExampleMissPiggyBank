package c16.mpb.bankingapp.service;

import org.springframework.stereotype.Service;

@Service
public class BsnChecker {

    public BsnChecker() {
        super();
    }

    public boolean isValidBsn(int bsn) {
        if (bsn <= 9999999 || bsn > 999999999) {
            return false;
        }
        int sum = -1 * bsn % 10;

        for (int multiplier = 2; bsn > 0; multiplier++) {
            int value = (bsn /= 10) % 10;
            sum += multiplier * value;
        }
        return sum != 0 && sum % 11 == 0;
    }
}
