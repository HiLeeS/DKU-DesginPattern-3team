package factory.pricing;

/**
 * 표준 취소 정책 구현 클래스
 * Abstract Factory 패턴의 Concrete Product
 * 
 * 일반적인 취소 정책
 */
public class StandardCancellation implements CancellationPolicy {
    @Override
    public int calculatePenalty(int totalPrice, int daysBeforeCancellation) {
        if (daysBeforeCancellation >= 7) {
            // 7일 전 취소: 10% 위약금
            return (int) (totalPrice * 0.10);
        } else if (daysBeforeCancellation >= 3) {
            // 3일 전 취소: 30% 위약금
            return (int) (totalPrice * 0.30);
        } else {
            // 당일 취소: 50% 위약금
            return (int) (totalPrice * 0.50);
        }
    }
}

