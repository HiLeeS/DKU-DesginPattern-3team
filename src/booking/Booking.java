package booking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Booking {
    private int bookingId;
    private int userId;
    private Date startDate;
    private Date endDate;
    private Status status;

    // 예약 상태 Enum
    public enum Status {
        PENDING, CONFIRMED, CANCELLED
    }

    public Booking(int bookingId, int userId, Date startDate, Date endDate, Status status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", userId=" + userId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                '}';
    }
}
