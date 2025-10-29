package observer;

/**
 * Observer 패턴을 위한 Observer 인터페이스.
 * 주체의 알림에 반응하는 구독자(Subscriber)를 나타냅니다.
 */
public interface Observer {
    /**
     * 이벤트 정보로 옵저버를 갱신합니다.
     * 
     * @param evt 이벤트 데이터를 포함하는 이벤트 객체
     */
    void update(Object evt);
}

