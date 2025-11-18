package factory.pricing;

/**
 * 할인 없는 기본 가격 전략 구현 클래스
 * Abstract Factory 패턴의 Concrete Product
 */
public class NoDiscount implements DiscountStrategy {
    @Override
    public int calculatePrice(int basePrice, int qty, int days) {
        return basePrice * qty * days;
    }
}

