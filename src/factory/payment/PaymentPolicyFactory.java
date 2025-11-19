package factory.payment;

import strategy.payment.PaymentStrategy;

/**
 * 결제 정책 팩토리를 나타내는 추상 팩토리 인터페이스
 * Abstract Factory 패턴의 Abstract Factory
 * 
 * 결제 전략과 결제 검증을 함께 생성하는 제품군을 정의합니다.
 */
public interface PaymentPolicyFactory {
    /**
     * 결제 전략을 생성합니다.
     * 
     * @return PaymentStrategy 구현 객체
     */
    PaymentStrategy createPaymentStrategy();
    
    /**
     * 결제 검증을 생성합니다.
     * 
     * @return PaymentVerification 구현 객체
     */
    PaymentVerification createPaymentVerification();
}

