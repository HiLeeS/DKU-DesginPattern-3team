package strategy.pricing;

public class LongTermDiscountStrategy implements IPricingStrategy {
    @Override
    public int calculatePrice(int basePrice, int qty, int days) {
        if (days > 30) {
            // 30일 이상 장기 대여: 30% 할인
            return (int) (basePrice * qty * days * 0.70);
        }
        if (days > 15) {
            // 15일 이상 장기 대여: 15% 할인
            return (int) (basePrice * qty * days * 0.85);
        }
        return basePrice * qty * days;
    }
}
