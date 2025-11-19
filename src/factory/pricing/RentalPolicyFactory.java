package factory.pricing;

/**
 * 대여 정책 팩토리를 나타내는 추상 팩토리 인터페이스
 * Abstract Factory 패턴의 Abstract Factory
 * 
 * 할인 전략과 취소 정책을 함께 생성하는 제품군을 정의합니다.
 */
public interface RentalPolicyFactory {
    /**
     * 할인 전략을 생성합니다.
     * 
     * @return DiscountStrategy 구현 객체
     */
    DiscountStrategy createDiscountStrategy();
    
    /**
     * 취소 정책을 생성합니다.
     * 
     * @return CancellationPolicy 구현 객체
     */
    CancellationPolicy createCancellationPolicy();
}

