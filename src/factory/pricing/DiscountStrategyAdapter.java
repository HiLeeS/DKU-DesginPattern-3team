package factory.pricing;

import strategy.pricing.IPricingStrategy;

/**
 * DiscountStrategy를 IPricingStrategy로 변환하는 어댑터 클래스
 * 기존 코드와의 호환성을 위해 사용됩니다.
 */
public class DiscountStrategyAdapter implements IPricingStrategy {
    private final DiscountStrategy discountStrategy;
    
    public DiscountStrategyAdapter(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }
    
    @Override
    public int calculatePrice(int basePrice, int qty, int days) {
        return discountStrategy.calculatePrice(basePrice, qty, days);
    }
}

