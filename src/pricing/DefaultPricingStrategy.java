package pricing;

public class DefaultPricingStrategy implements IPricingStrategy {
    // 기본 요금 계산: 기본 * 수량 * 대여일수
    @Override
    public int calculatePrice(int basePrice, int qty, int days) {
        return basePrice * qty * days;
    }
}
