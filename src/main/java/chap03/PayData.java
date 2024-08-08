package chap03;

import java.time.LocalDate;

public class PayData {

    private LocalDate firstBillingDate;
    private LocalDate billingDate;
    private int paymentAmount;

    public PayData() {
    }

    public PayData(LocalDate firstBillingDate, LocalDate billingDate, int paymentAmount) {
        this.firstBillingDate = firstBillingDate;
        this.billingDate = billingDate;
        this.paymentAmount = paymentAmount;
    }

    public LocalDate getFirstBillingDate() {
        return firstBillingDate;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public int getAddedMonths(int price12Month, int price1Month) {
        int add1Month = (paymentAmount % price12Month) / price1Month;
        int add12Month = paymentAmount / price12Month;
        return (add1Month + 12 * add12Month);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PayData data = new PayData();

        public Builder firstBillingDate(LocalDate firstDate) {
            data.firstBillingDate = firstDate;
            return this;
        }

        public Builder billingDate(LocalDate billingDate) {
            data.billingDate = billingDate;
            return this;
        }

        public Builder payAmount(int payAmount) {
            data.paymentAmount = payAmount;
            return this;
        }

        public PayData build() {
            return data;
        }
    }
}
