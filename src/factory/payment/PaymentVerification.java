package factory.payment;

import strategy.payment.PaymentMethod;

/**
 * 결제 검증을 나타내는 추상 제품 인터페이스
 * Abstract Factory 패턴의 Abstract Product
 */
public interface PaymentVerification {
    /**
     * 결제를 검증합니다.
     * 
     * @param amount 결제 금액
     * @param paymentMethod 결제 수단
     * @return 검증 성공 여부
     */
    boolean verify(int amount, PaymentMethod paymentMethod);
}

