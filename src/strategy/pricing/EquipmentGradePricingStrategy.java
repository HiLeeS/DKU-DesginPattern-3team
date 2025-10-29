package strategy.pricing;

/**
 * 장비 등급에 따라 요금을 조정하는 요금제입니다.
 * 이 클래스는 다른 IPricingStrategy를 감싸는 데코레이터(Decorator)로 구현되어,
 * 기존 요금 계산에 등급별 할증을 추가합니다.
 */
public class EquipmentGradePricingStrategy implements IPricingStrategy {

    private final IPricingStrategy wrappedStrategy;
    private final String grade;

    /**
     * @param wrappedStrategy 꾸밀(decorate) 기본 요금제
     * @param grade 장비 등급 ("HIGH", "NORMAL", "LOW")
     */
    public EquipmentGradePricingStrategy(IPricingStrategy wrappedStrategy, String grade) {
        this.wrappedStrategy = wrappedStrategy;
        this.grade = grade;
    }

    @Override
    public int calculatePrice(int basePrice, int qty, int days) {
        // 1. 먼저 감싸고 있는(wrapped) 전략으로 기본 요금을 계산합니다.
        int initialPrice = wrappedStrategy.calculatePrice(basePrice, qty, days);

        // 2. 등급에 따라 할증률을 결정합니다.
        double gradeMultiplier = 1.0;
        if (grade != null) {
            switch (grade.toUpperCase()) {
                case "HIGH":
                    gradeMultiplier = 1.25; // 고급 장비 25% 할증
                    break;
                case "NORMAL":
                    gradeMultiplier = 1.10; // 일반 장비 10% 할증
                    break;
                // "LOW" 등급은 할증 없음
            }
        }

        // 3. 등급별 할증을 적용한 최종 요금을 반환합니다.
        return (int) (initialPrice * gradeMultiplier);
    }
}
