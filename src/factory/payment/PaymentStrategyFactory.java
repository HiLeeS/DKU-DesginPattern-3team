package factory.payment;

import strategy.payment.PaymentMethod;

/**
 * 결제 전략 객체를 생성하는 팩토리 클래스
 * Abstract Factory 패턴을 사용하여 결제 정책 팩토리를 선택하고 제품을 생성합니다.
 * 클라이언트가 결제 수단 타입만 전달하면 적절한 결제 전략 객체를 반환합니다.
 */
public class PaymentStrategyFactory {
    
    /**
     * 결제 수단 타입에 따라 적절한 결제 정책 팩토리를 반환합니다.
     * 
     * @param method 결제 수단 타입 (CARD, KAKAO, NAVER)
     * @return 해당 결제 수단에 맞는 PaymentPolicyFactory 구현 객체
     * @throws IllegalArgumentException 지원하지 않는 결제 수단인 경우
     */
    public static PaymentPolicyFactory getPaymentPolicyFactory(PaymentMethod method) {
        if (method == null) {
            throw new IllegalArgumentException("결제 수단이 지정되지 않았습니다.");
        }
        
        switch (method) {
            case CARD:
                return new CardPaymentFactory();
            case KAKAO:
                return new KakaoPaymentFactory();
            case NAVER:
                return new NaverPaymentFactory();
            default:
                throw new IllegalArgumentException("지원하지 않는 결제 수단입니다: " + method);
        }
    }
    
    /**
     * 결제 수단 타입에 따라 적절한 결제 전략 객체를 생성하여 반환합니다.
     * (하위 호환성을 위한 메서드 - Abstract Factory 패턴 사용)
     * 
     * @param method 결제 수단 타입 (CARD, KAKAO, NAVER)
     * @return 해당 결제 수단에 맞는 PaymentStrategy 구현 객체
     * @throws IllegalArgumentException 지원하지 않는 결제 수단인 경우
     */
    public static strategy.payment.PaymentStrategy getPaymentStrategy(PaymentMethod method) {
        PaymentPolicyFactory factory = getPaymentPolicyFactory(method);
        return factory.createPaymentStrategy();
    }
}

