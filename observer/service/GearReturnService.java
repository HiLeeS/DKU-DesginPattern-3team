package service;

import event.GearReturned;
import observer.Observer;
import observer.Subject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 장비 반납 작업을 담당하는 서비스입니다.
 * Observer 패턴에서 주체(Subject) 역할을 합니다.
 */
public class GearReturnService implements Subject {
    private List<Observer> observers;

    public GearReturnService() {
        this.observers = new ArrayList<>();
    }

    /**
     * 사용자로부터 장비를 반납받습니다.
     * 
     * @param setId 장비 세트 ID
     * @param userId 사용자 ID
     * @param actualReturn 실제 반납 날짜 및 시간
     */
    public void returnGear(int setId, int userId, LocalDateTime actualReturn) {
        System.out.println("[GearReturnService] 반납 처리 중 - 세트 ID: " + setId + 
                          ", 사용자 ID: " + userId + ", 반납 시간: " + actualReturn);
        
        // 반납 이벤트 생성
        GearReturned event = new GearReturned(setId, userId, actualReturn);
        
        // 모든 옵저버에게 알림
        notify(event);
    }

    /**
     * 옵저버들에게 GearReturned 이벤트를 알립니다.
     * 
     * @param evt GearReturned 이벤트 객체
     */
    public void notify(Object evt) {
        System.out.println("[GearReturnService] " + observers.size() + "개의 옵저버에게 알림");
        for (Observer observer : observers) {
            observer.update(evt);
        }
    }

    @Override
    public void attach(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
            System.out.println("[GearReturnService] Observer attached");
        }
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
        System.out.println("[GearReturnService] Observer detached");
    }
}

