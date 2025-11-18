package factory.payment;

import strategy.payment.PaymentMethod;

/**
 * 카드 결제 검증 구현 클래스
 * Abstract Factory 패턴의 Concrete Product
 */
public class CardPaymentVerification implements PaymentVerification {
    @Override
    public boolean verify(int amount, PaymentMethod paymentMethod) {
        if (amount <= 0) {
            System.out.println("[CardPaymentVerification] 결제 금액이 유효하지 않습니다.");
            return false;
        }
        if (amount > 10000000) {
            System.out.println("[CardPaymentVerification] 카드 결제 한도(1천만원)를 초과했습니다.");
            return false;
        }
        System.out.println("[CardPaymentVerification] 카드 결제 검증 완료: " + amount + "원");
        return true;
    }
}

