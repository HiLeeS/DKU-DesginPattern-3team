package strategy.payment;

public class NaverPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("[NaverPayment] 네이버페이로 " + amount + "원 결제 완료.");
    }
}