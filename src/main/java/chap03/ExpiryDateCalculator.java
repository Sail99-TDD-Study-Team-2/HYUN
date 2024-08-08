package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths = payData.getAddedMonths(100_000,10_000);
        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        }
        return payData.getBillingDate().plusMonths(addedMonths);
    }

    private static LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
        final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();
        return candidateExp.withDayOfMonth(Math.min(dayLenOfCandiMon, payData.getFirstBillingDate().getDayOfMonth()));
    }


}
