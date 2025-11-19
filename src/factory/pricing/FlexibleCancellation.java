package factory.pricing;

/**
 * 유연한 취소 정책 구현 클래스
 * Abstract Factory 패턴의 Concrete Product
 * 
 * 취소 시 위약금이 적거나 없는 정책
 */
public class FlexibleCancellation implements CancellationPolicy {
    @Override
    public int calculatePenalty(int totalPrice, int daysBeforeCancellation) {
        if (daysBeforeCancellation >= 7) {
            // 7일 전 취소: 위약금 없음
            return 0;
        } else if (daysBeforeCancellation >= 3) {
            // 3일 전 취소: 10% 위약금
            return (int) (totalPrice * 0.10);
        } else {
            // 당일 취소: 20% 위약금
            return (int) (totalPrice * 0.20);
        }
    }
}

