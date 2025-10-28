import pricing.*;

public class Main {
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
        int longTermPriceMid = longTermStrategy.calculatePrice(basePrice, qty, midTermDays);
        int longTermPriceLong = longTermStrategy.calculatePrice(basePrice, qty, longTermDays);
        System.out.println("[장기 할인 요금제]");
        System.out.printf("  - 20일 대여 시 (15%% 할인): %,d원\n", longTermPriceMid);
        System.out.printf("  - 35일 대여 시 (30%% 할인): %,d원\n\n", longTermPriceLong);

        // 3. 성수기 요금제 테스트
        IPricingStrategy peakSeasonStrategy = new PeakSeasonPricingStrategy();
        int peakPrice = peakSeasonStrategy.calculatePrice(basePrice, qty, shortTermDays);
        System.out.println("[성수기 요금제]");
        System.out.printf("  - 5일 대여 시 (20%% 할증): %,d원\n\n", peakPrice);

        // 4. 장비 등급 요금제 테스트 (데코레이터 패턴)
        System.out.println("[장비 등급 요금제 (데코레이터)]");
        // 4-1. 기본 요금 + 고급 장비 할증
        IPricingStrategy highGradeDefaultPrice = new EquipmentGradePricingStrategy(defaultStrategy, "HIGH");
        int decoratedPrice1 = highGradeDefaultPrice.calculatePrice(basePrice, qty, shortTermDays);
        System.out.printf("  - 기본 요금 + 고급 장비(25%% 할증): %,d원\n", decoratedPrice1);

        // 4-2. 성수기 요금 + 고급 장비 할증 (중첩 적용)
        IPricingStrategy highGradePeakPrice = new EquipmentGradePricingStrategy(peakSeasonStrategy, "HIGH");
        int decoratedPrice2 = highGradePeakPrice.calculatePrice(basePrice, qty, shortTermDays);
        System.out.printf("  - 성수기 요금 + 고급 장비(25%% 할증): %,d원\n", decoratedPrice2);
    }
}
