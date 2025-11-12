package observer.impl;

import event.GearRented;
import event.GearReturned;
import observer.Observer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 결제 관련 작업을 처리하는 옵저버입니다.
 */
public class PaymentObserver implements Observer {
    
    private static final BigDecimal DAILY_RATE = new BigDecimal("50.00"); // 일일 대여료
    private static final BigDecimal LATE_FEE_PER_DAY = new BigDecimal("10.00"); // 일일 연체료
    private static final BigDecimal DEPOSIT_AMOUNT = new BigDecimal("100.00"); // 보증금

    @Override
    public void update(Object evt) {
        if (evt instanceof GearRented) {
            onGearRented((GearRented) evt);
        } else if (evt instanceof GearReturned) {
            onGearReturned((GearReturned) evt);
        }
    }

    /**
     * 결제 관점에서 장비 대여 이벤트를 처리합니다.
     * 
     * @param evt 장비 대여 이벤트
     */
    public void onGearRented(GearRented evt) {
        System.out.println("[PaymentObserver] 대여 결제 처리:");
        System.out.println("  - 세트 ID: " + evt.getSetId());
        System.out.println("  - 사용자 ID: " + evt.getUserId());
        
        confirmAmount(evt);
    }

    /**
     * 대여에 대한 결제 금액을 확인합니다.
     * 
     * @param evt 장비 대여 이벤트
     */
    public void confirmAmount(GearRented evt) {
        long days = ChronoUnit.DAYS.between(evt.getFrom(), evt.getTo());
        if (days == 0) days = 1; // 최소 1일
        
        BigDecimal rentalAmount = DAILY_RATE.multiply(BigDecimal.valueOf(days));
        BigDecimal deposit = DEPOSIT_AMOUNT;
        BigDecimal totalAmount = rentalAmount.add(deposit);
        
        System.out.println("  → 대여 기간: " + days + "일");
        System.out.println("  → 대여료: $" + rentalAmount);
        System.out.println("  → 보증금: $" + deposit);
        System.out.println("  → 총 청구 금액: $" + totalAmount);
    }

    /**
     * 결제 관점에서 장비 반납 이벤트를 처리합니다.
     * 
     * @param evt 장비 반납 이벤트
     */
    public void onGearReturned(GearReturned evt) {
        System.out.println("[PaymentObserver] 반납 결제 처리:");
        System.out.println("  - 세트 ID: " + evt.getSetId());
        System.out.println("  - 사용자 ID: " + evt.getUserId());
        
        // 참고: 실제 시나리오에서는 연체료 계산을 위해 대여 세부 정보를 가져와야 합니다
        // 데모 목적으로 메서드만 호출합니다
        settleLateFee(evt);
        refundDeposit(evt);
    }

    /**
     * 반납이 연체된 경우 연체료를 정산합니다.
     * 
     * @param evt 장비 반납 이벤트
     */
    public void settleLateFee(GearReturned evt) {
        // 실제 시나리오에서는 예상 반납 날짜와 비교하여
        // 실제 연체료를 계산합니다
        LocalDateTime actualReturn = evt.getActualReturn();
        System.out.println("  → 반납 시간 기반 연체료 확인: " + actualReturn);
        System.out.println("  → 연체료(해당 시): 일일 $" + LATE_FEE_PER_DAY);
        // 이 예제에서는 연체료가 없다고 가정
        System.out.println("  → 연체료 정산: $0.00 (지연 없음)");
    }

    /**
     * 보증금을 환불합니다.
     * 
     * @param evt 장비 반납 이벤트
     */
    public void refundDeposit(GearReturned evt) {
        System.out.println("  → 보증금 환불: $" + DEPOSIT_AMOUNT);
        System.out.println("  → 보증금 환불 완료");
    }
}

