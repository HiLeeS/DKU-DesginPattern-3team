package factory.payment;

import strategy.payment.PaymentMethod;

/**
 * 네이버페이 결제 검증 구현 클래스
 * Abstract Factory 패턴의 Concrete Product
 */
public class NaverPaymentVerification implements PaymentVerification {
    @Override
    public boolean verify(int amount, PaymentMethod paymentMethod) {
        if (amount <= 0) {
            System.out.println("[NaverPaymentVerification] 결제 금액이 유효하지 않습니다.");
            return false;
        }
        if (amount > 3000000) {
            System.out.println("[NaverPaymentVerification] 네이버페이 결제 한도(3백만원)를 초과했습니다.");
            return false;
        }
        System.out.println("[NaverPaymentVerification] 네이버페이 결제 검증 완료: " + amount + "원");
        return true;
    }
}

