package observer.impl;

import event.GearRented;
import event.GearReturned;
import observer.Observer;

/**
 * 재고 관리 관련 작업을 처리하는 옵저버입니다.
 */
public class InventoryObserver implements Observer {
    
    @Override
    public void update(Object evt) {
        if (evt instanceof GearRented) {
            onGearRented((GearRented) evt);
        } else if (evt instanceof GearReturned) {
            onGearReturned((GearReturned) evt);
        }
    }

    /**
     * 재고 관점에서 장비 대여 이벤트를 처리합니다.
     * 
     * @param evt 장비 대여 이벤트
     */
    public void onGearRented(GearRented evt) {
        System.out.println("[InventoryObserver] 장비 대여 처리:");
        System.out.println("  - 세트 ID: " + evt.getSetId());
        System.out.println("  - 사용자 ID: " + evt.getUserId());
        System.out.println("  - 수량: " + evt.getQty());
        System.out.println("  → 재고에서 " + evt.getQty() + "개 감소");
    }

    /**
     * 재고 관점에서 장비 반납 이벤트를 처리합니다.
     * 
     * @param evt 장비 반납 이벤트
     */
    public void onGearReturned(GearReturned evt) {
        System.out.println("[InventoryObserver] 장비 반납 처리:");
        System.out.println("  - 세트 ID: " + evt.getSetId());
        System.out.println("  - 사용자 ID: " + evt.getUserId());
        System.out.println("  - 실제 반납 시간: " + evt.getActualReturn());
        System.out.println("  → 재고에 1개 증가");
    }
}

