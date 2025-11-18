package factory.pricing;

/**
 * 성수기 일반 팩토리 구현 클래스
 * Abstract Factory 패턴의 Concrete Factory
 * 
 * 할인 없음과 엄격한 취소 정책을 제공하는 제품군을 생성합니다.
 */
public class PeakRegularFactory implements RentalPolicyFactory {
    @Override
    public DiscountStrategy createDiscountStrategy() {
        return new NoDiscount();
    }
    
    @Override
    public CancellationPolicy createCancellationPolicy() {
        return new StrictCancellation();
    }
}

