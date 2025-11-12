package observer;

/**
 * Observer 패턴을 위한 Subject 인터페이스.
 * 상태 변경이 있을 때 옵저버들에게 알리는 발행자(Publisher)를 나타냅니다.
 */
public interface Subject {
    /**
     * 이 주체에 옵저버를 등록합니다.
     * 
     * @param o 등록할 옵저버
     */
    void attach(Observer o);

    /**
     * 이 주체에서 옵저버를 해제합니다.
     * 
     * @param o 해제할 옵저버
     */
    void detach(Observer o);

    /**
     * 등록된 모든 옵저버에게 이벤트를 알립니다.
     * 
     * @param evt 이벤트 데이터를 포함하는 이벤트 객체
     */
    void notify(Object evt);
}

