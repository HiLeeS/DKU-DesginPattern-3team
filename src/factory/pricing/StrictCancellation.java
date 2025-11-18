package factory.pricing;

/**
 * 엄격한 취소 정책 구현 클래스
 * Abstract Factory 패턴의 Concrete Product
 * 
 * 취소 시 높은 위약금이 부과되는 정책
 */
public class StrictCancellation implements CancellationPolicy {
    @Override
    public int calculatePenalty(int totalPrice, int daysBeforeCancellation) {
        if (daysBeforeCancellation >= 14) {
            // 14일 전 취소: 20% 위약금
            return (int) (totalPrice * 0.20);
        } else if (daysBeforeCancellation >= 7) {
            // 7일 전 취소: 50% 위약금
            return (int) (totalPrice * 0.50);
        } else {
            // 당일 취소: 100% 위약금 (환불 불가)
            return totalPrice;
        }
    }
}

