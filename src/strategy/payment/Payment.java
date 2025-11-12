package strategy.payment;

public class Payment {

    private int paymentId; // 결제 ID
    private int bookingId; // 예약 ID
    private int amount; // 결제 금액
    private PaymentStatus status; // 결제 상태
    private PaymentStrategy strategy; // 결제 전략

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

    public PaymentStatus getStatus() {
        return status;
    }
}
