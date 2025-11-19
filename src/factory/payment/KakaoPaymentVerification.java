package factory.payment;

import strategy.payment.PaymentMethod;

/**
 * 카카오페이 결제 검증 구현 클래스
 * Abstract Factory 패턴의 Concrete Product
 */
public class KakaoPaymentVerification implements PaymentVerification {
    @Override
    public boolean verify(int amount, PaymentMethod paymentMethod) {
        if (amount <= 0) {
            System.out.println("[KakaoPaymentVerification] 결제 금액이 유효하지 않습니다.");
            return false;
        }
        if (amount > 5000000) {
            System.out.println("[KakaoPaymentVerification] 카카오페이 결제 한도(5백만원)를 초과했습니다.");
            return false;
        }
        System.out.println("[KakaoPaymentVerification] 카카오페이 결제 검증 완료: " + amount + "원");
        return true;
    }
}

