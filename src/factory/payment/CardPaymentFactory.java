package factory.payment;

import strategy.payment.CardPayment;
import strategy.payment.PaymentStrategy;

/**
 * 카드 결제 팩토리 구현 클래스
 * Abstract Factory 패턴의 Concrete Factory
 * 
 * 카드 결제 전략과 카드 결제 검증을 제공하는 제품군을 생성합니다.
 */
public class CardPaymentFactory implements PaymentPolicyFactory {
    @Override
    public PaymentStrategy createPaymentStrategy() {
        return new CardPayment();
    }
    
    @Override
    public PaymentVerification createPaymentVerification() {
        return new CardPaymentVerification();
    }
}

