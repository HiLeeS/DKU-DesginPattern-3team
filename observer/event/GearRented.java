package event;

import java.time.LocalDateTime;

/**
 * 장비 대여를 나타내는 이벤트 데이터 클래스입니다.
 */
public class GearRented {
    private int setId;
    private int userId;
    private LocalDateTime from;
    private LocalDateTime to;
    private int qty;

    public GearRented(int setId, int userId, LocalDateTime from, LocalDateTime to, int qty) {
        this.setId = setId;
        this.userId = userId;
        this.from = from;
        this.to = to;
        this.qty = qty;
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

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "GearRented{" +
                "setId=" + setId +
                ", userId=" + userId +
                ", from=" + from +
                ", to=" + to +
                ", qty=" + qty +
                '}';
    }
}

