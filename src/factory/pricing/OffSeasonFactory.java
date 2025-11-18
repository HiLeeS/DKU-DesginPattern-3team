package factory.pricing;

/**
 * 비수기 팩토리 구현 클래스
 * Abstract Factory 패턴의 Concrete Factory
 * 
 * 시즌 할인과 표준 취소 정책을 제공하는 제품군을 생성합니다.
 */
public class OffSeasonFactory implements RentalPolicyFactory {
    @Override
    public DiscountStrategy createDiscountStrategy() {
        return new SeasonalDiscount();
    }
    
    @Override
    public CancellationPolicy createCancellationPolicy() {
        return new StandardCancellation();
    }
}

