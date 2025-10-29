public class KakaoPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("[KakaoPayment] 카카오페이로 " + amount + "원 결제 완료.");
    }
}