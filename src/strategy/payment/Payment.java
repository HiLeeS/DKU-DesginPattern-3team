package strategy.payment;

import factory.payment.PaymentPolicyFactory;
import factory.payment.PaymentStrategyFactory;
import factory.payment.PaymentVerification;

/**
 * 결제를 처리하는 클래스
 * Abstract Factory 패턴을 사용하여 결제 전략과 검증을 함께 사용합니다.
 */
public class Payment {

    private int paymentId; // 결제 ID
    private int bookingId; // 예약 ID
    private int amount; // 결제 금액
    private PaymentStatus status; // 결제 상태
    private PaymentStrategy strategy; // 결제 전략
    private PaymentVerification verification; // 결제 검증

    public Payment(int paymentId, int bookingId, int amount) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.amount = amount;
    }

    /**
     * 결제 수단을 설정합니다. Abstract Factory 패턴을 사용하여
     * 결제 전략과 검증을 함께 생성합니다.
     * 
     * @param method 결제 수단 타입
     */
    public void setPaymentMethod(PaymentMethod method) {
        PaymentPolicyFactory factory = PaymentStrategyFactory.getPaymentPolicyFactory(method);
        this.strategy = factory.createPaymentStrategy();
        this.verification = factory.createPaymentVerification();
    }

    /**
     * 결제 전략을 직접 설정합니다. (하위 호환성을 위한 메서드)
     * 
     * @param strategy 결제 전략
     */
    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 결제를 처리합니다.
     * Abstract Factory 패턴을 사용하여 생성된 검증과 전략을 사용합니다.
     */
    public void pay() {
        if (strategy == null) {
            System.out.println("결제 전략이 설정되지 않았습니다.");
            return;
        }
        
        // 검증이 설정된 경우 검증 수행
        if (verification != null) {
            PaymentMethod method = getPaymentMethodFromStrategy();
            if (!verification.verify(amount, method)) {
                System.out.println("결제 검증에 실패했습니다.");
                this.status = PaymentStatus.FAILED;
                return;
            }
        }
        
        strategy.pay(amount);
        this.status = PaymentStatus.AUTHORIZED;
    }

    /**
     * 전략에서 결제 수단을 추론합니다.
     * (실제 구현에서는 더 정교한 방법이 필요할 수 있습니다)
     */
    private PaymentMethod getPaymentMethodFromStrategy() {
        if (strategy instanceof CardPayment) {
            return PaymentMethod.CARD;
        } else if (strategy instanceof KakaoPayment) {
            return PaymentMethod.KAKAO;
        } else if (strategy instanceof NaverPayment) {
            return PaymentMethod.NAVER;
        }
        return PaymentMethod.CARD; // 기본값
    }

    public PaymentStatus getStatus() {
        return status;
    }
}
