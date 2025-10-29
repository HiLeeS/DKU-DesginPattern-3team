package service;

import event.GearRented;
import observer.Observer;
import observer.Subject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 장비 대여 작업을 담당하는 서비스입니다.
 * Observer 패턴에서 주체(Subject) 역할을 합니다.
 */
public class GearRentalService implements Subject {
    private List<Observer> observers;

    public GearRentalService() {
        this.observers = new ArrayList<>();
    }

    /**
     * 사용자를 위해 장비를 대여합니다.
     * 
     * @param setId 장비 세트 ID
     * @param userId 사용자 ID
     * @param from 대여 시작 날짜 및 시간
     * @param to 대여 종료 날짜 및 시간
     * @param qty 장비 수량
     */
    public void rent(int setId, int userId, LocalDateTime from, LocalDateTime to, int qty) {
        System.out.println("[GearRentalService] 대여 처리 중 - 세트 ID: " + setId + 
                          ", 사용자 ID: " + userId + ", 수량: " + qty);
        
        // 대여 이벤트 생성
        GearRented event = new GearRented(setId, userId, from, to, qty);
        
        // 모든 옵저버에게 알림
        notify(event);
    }

    /**
     * 옵저버들에게 GearRented 이벤트를 알립니다.
     * 
     * @param evt GearRented 이벤트 객체
     */
    public void notify(Object evt) {
        System.out.println("[GearRentalService] " + observers.size() + "개의 옵저버에게 알림");
        for (Observer observer : observers) {
            observer.update(evt);
        }
    }

    @Override
    public void attach(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
            System.out.println("[GearRentalService] Observer attached");
        }
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
        System.out.println("[GearRentalService] Observer detached");
    }
}

