package factory.pricing;

import strategy.pricing.PricingContext;

/**
 * 상황 정보(Context)를 기반으로 적절한 대여 정책 팩토리를 선택하는 팩토리 클래스
 * Abstract Factory 패턴을 사용하여 대여 정책 팩토리를 선택하고 제품군을 생성합니다.
 * RatePlan은 복잡한 조건문 없이 가격 계산에만 집중할 수 있도록 합니다.
 */
public class PricingStrategyFactory {
    
    /**
     * 상황 정보를 기반으로 가장 적합한 대여 정책 팩토리를 반환합니다.
     * 
     * 선택 기준:
     * 1. 성수기 + VIP -> PeakVIPFactory
     * 2. 성수기 + 일반 -> PeakRegularFactory
     * 3. 비수기 -> OffSeasonFactory
     * 
     * @param context 가격 정책 결정을 위한 상황 정보 (날짜, 대여 기간, 성수기 여부 등)
     * @param isVIP VIP 여부 (기본값: false)
     * @return 상황에 맞는 RentalPolicyFactory 구현 객체
     */
    public static RentalPolicyFactory getRentalPolicyFactory(PricingContext context, boolean isVIP) {
        if (context == null) {
            return new OffSeasonFactory();
        }
        
        // 성수기 여부 확인
        if (context.isPeakSeason()) {
            if (isVIP) {
                return new PeakVIPFactory();
            } else {
                return new PeakRegularFactory();
            }
        } else {
            return new OffSeasonFactory();
        }
    }
    
    /**
     * 상황 정보를 기반으로 가장 적합한 대여 정책 팩토리를 반환합니다.
     * (VIP 여부 기본값: false)
     * 
     * @param context 가격 정책 결정을 위한 상황 정보
     * @return 상황에 맞는 RentalPolicyFactory 구현 객체
     */
    public static RentalPolicyFactory getRentalPolicyFactory(PricingContext context) {
        return getRentalPolicyFactory(context, false);
    }
}

