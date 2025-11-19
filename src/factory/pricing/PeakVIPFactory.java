package factory.pricing;

/**
 * 성수기 VIP 팩토리 구현 클래스
 * Abstract Factory 패턴의 Concrete Factory
 * 
 * VIP 할인과 유연한 취소 정책을 제공하는 제품군을 생성합니다.
 */
public class PeakVIPFactory implements RentalPolicyFactory {
    @Override
    public DiscountStrategy createDiscountStrategy() {
        return new VIPDiscount();
    }
    
    @Override
    public CancellationPolicy createCancellationPolicy() {
        return new FlexibleCancellation();
    }
}

