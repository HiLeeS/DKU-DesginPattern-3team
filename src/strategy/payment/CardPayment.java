package strategy.payment;

public class CardPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("[CardPayment] 카드로 " + amount + "원 결제 완료.");
    }
}