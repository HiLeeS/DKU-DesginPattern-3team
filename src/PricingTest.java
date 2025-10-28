import pricing.DefaultPricingStrategy;
import pricing.IPricingStrategy;
import pricing.LongTermDiscountStrategy;
import pricing.PeakSeasonPricingStrategy;

public class PricingTest {
    public static void main(String[] args) {
        int basePrice = 10000; // 1일 기본 요금: 10,000원
        int qty = 2;           // 수량: 2개
        int shortTermDays = 5;   // 단기 대여: 5일
        int midTermDays = 20;  // 중기 대여: 20일
        int longTermDays = 35;   // 장기 대여: 35일

        System.out.println("===== 요금 계산 시나리오 =====");
        System.out.printf("기본 정보: 1일 요금=%,d원, 수량=%d개\n\n", basePrice, qty);

        // 1. 기본 요금제 테스트
        IPricingStrategy defaultStrategy = new DefaultPricingStrategy();
        int defaultPrice = defaultStrategy.calculatePrice(basePrice, qty, shortTermDays);
        System.out.println("[기본 요금제]");
        System.out.printf("  - 5일 대여 시: %,d원\n\n", defaultPrice);

        // 2. 장기 할인 요금제 테스트
        IPricingStrategy longTermStrategy = new LongTermDiscountStrategy();
        int longTermPriceShort = longTermStrategy.calculatePrice(basePrice, qty, shortTermDays); // 5일 (할인 없음)
        int longTermPriceMid = longTermStrategy.calculatePrice(basePrice, qty, midTermDays);   // 20일 (15% 할인)
        int longTermPriceLong = longTermStrategy.calculatePrice(basePrice, qty, longTermDays);    // 35일 (30% 할인)

        System.out.println("[장기 할인 요금제]");
        System.out.printf("  - 5일 대여 시 (할인 없음): %,d원\n", longTermPriceShort);
        System.out.printf("  - 20일 대여 시 (15%% 할인): %,d원\n", longTermPriceMid);
        System.out.printf("  - 35일 대여 시 (30%% 할인): %,d원\n\n", longTermPriceLong);

        // 3. 성수기 요금제 테스트
        IPricingStrategy peakSeasonStrategy = new PeakSeasonPricingStrategy();
        int peakPrice = peakSeasonStrategy.calculatePrice(basePrice, qty, shortTermDays);
        System.out.println("[성수기 요금제]");
        System.out.printf("  - 5일 대여 시 (20%% 할증): %,d원\n", peakPrice);
    }
}
