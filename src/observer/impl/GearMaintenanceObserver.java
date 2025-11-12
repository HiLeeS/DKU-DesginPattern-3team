package observer.impl;

import event.GearReturned;
import observer.Observer;

/**
 * 유지보수 관련 작업을 처리하는 옵저버입니다.
 */
public class GearMaintenanceObserver implements Observer {
    
    @Override
    public void update(Object evt) {
        if (evt instanceof GearReturned) {
            onGearReturned((GearReturned) evt);
        }
    }

    /**
     * 유지보수 관점에서 장비 반납 이벤트를 처리합니다.
     * 
     * @param evt 장비 반납 이벤트
     */
    public void onGearReturned(GearReturned evt) {
        System.out.println("[GearMaintenanceObserver] 반납 유지보수 처리:");
        System.out.println("  - 세트 ID: " + evt.getSetId());
        System.out.println("  - 사용자 ID: " + evt.getUserId());
        System.out.println("  - 실제 반납: " + evt.getActualReturn());
        
        enqueueInspection(evt);
    }

    /**
     * 반납된 장비에 대한 점검을 대기열에 추가합니다.
     * 
     * @param evt 장비 반납 이벤트
     */
    public void enqueueInspection(GearReturned evt) {
        System.out.println("  → 장비 세트 ID 점검 대기열 추가: " + evt.getSetId());
        System.out.println("  → 점검 대기열: 세트 " + evt.getSetId() + "를 유지보수 대기열에 추가");
        System.out.println("  → 손상 평가 및 청소를 위한 점검 예약됨");
    }
}

