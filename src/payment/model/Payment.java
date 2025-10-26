package payment.model;

import payment.strategy.PaymentStrategy;

public class Payment {

    private int paymentId;
    private int bookingId;
    private int amount;
    private PaymentStatus status;
    private PaymentStrategy strategy;

    public Payment(int paymentId, int bookingId, int amount) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.amount = amount;
    }

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void pay() {
        if (strategy == null) {
            System.out.println("결제 전략이 설정되지 않았습니다.");
            return;
        }
        strategy.pay(amount);
        this.status = PaymentStatus.AUTHORIZED;
    }
}
