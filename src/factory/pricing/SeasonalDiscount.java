package factory.pricing;

/**
 * 시즌 할인 전략 구현 클래스
 * Abstract Factory 패턴의 Concrete Product
 */
public class SeasonalDiscount implements DiscountStrategy {
    // 시즌 할인율: 15%
    private static final double SEASONAL_DISCOUNT_RATE = 0.85;
    
    @Override
    public int calculatePrice(int basePrice, int qty, int days) {
        return (int) (basePrice * qty * days * SEASONAL_DISCOUNT_RATE);
    }
}

