package factory.pricing;

/**
 * VIP 할인 전략 구현 클래스
 * Abstract Factory 패턴의 Concrete Product
 */
public class VIPDiscount implements DiscountStrategy {
    // VIP 할인율: 30%
    private static final double VIP_DISCOUNT_RATE = 0.70;
    
    @Override
    public int calculatePrice(int basePrice, int qty, int days) {
        return (int) (basePrice * qty * days * VIP_DISCOUNT_RATE);
    }
}

