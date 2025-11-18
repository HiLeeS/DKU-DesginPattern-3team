package factory.pricing;

/**
 * 할인 전략을 나타내는 추상 제품 인터페이스
 * Abstract Factory 패턴의 Abstract Product
 */
public interface DiscountStrategy {
    /**
     * 가격을 계산합니다.
     * 
     * @param basePrice 기본 가격
     * @param qty 수량
     * @param days 대여 일수
     * @return 계산된 가격
     */
    int calculatePrice(int basePrice, int qty, int days);
}

