package factory.pricing;

/**
 * 취소 정책을 나타내는 추상 제품 인터페이스
 * Abstract Factory 패턴의 Abstract Product
 */
public interface CancellationPolicy {
    /**
     * 취소 시 위약금을 계산합니다.
     * 
     * @param totalPrice 총 결제 금액
     * @param daysBeforeCancellation 취소까지 남은 일수
     * @return 위약금 금액
     */
    int calculatePenalty(int totalPrice, int daysBeforeCancellation);
}

