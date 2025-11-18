package factory.payment;

import strategy.payment.KakaoPayment;
import strategy.payment.PaymentStrategy;

/**
 * 카카오페이 결제 팩토리 구현 클래스
 * Abstract Factory 패턴의 Concrete Factory
 * 
 * 카카오페이 결제 전략과 카카오페이 결제 검증을 제공하는 제품군을 생성합니다.
 */
public class KakaoPaymentFactory implements PaymentPolicyFactory {
    @Override
    public PaymentStrategy createPaymentStrategy() {
        return new KakaoPayment();
    }
    
    @Override
    public PaymentVerification createPaymentVerification() {
        return new KakaoPaymentVerification();
    }
}

