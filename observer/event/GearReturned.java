package event;

import java.time.LocalDateTime;

/**
 * 장비 반납을 나타내는 이벤트 데이터 클래스입니다.
 */
public class GearReturned {
    private int setId;
    private int userId;
    private LocalDateTime actualReturn;

    public GearReturned(int setId, int userId, LocalDateTime actualReturn) {
        this.setId = setId;
        this.userId = userId;
        this.actualReturn = actualReturn;
    }

    public int getSetId() {
        return setId;
    }

    public void setSetId(int setId) {
        this.setId = setId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getActualReturn() {
        return actualReturn;
    }

    public void setActualReturn(LocalDateTime actualReturn) {
        this.actualReturn = actualReturn;
    }

    @Override
    public String toString() {
        return "GearReturned{" +
                "setId=" + setId +
                ", userId=" + userId +
                ", actualReturn=" + actualReturn +
                '}';
    }
}

