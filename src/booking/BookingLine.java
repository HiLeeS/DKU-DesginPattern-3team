package booking;

public class BookingLine {
    private int lineId;
    private int bookingId;   // Booking과 연결
    private int gearSetId;
    private int qty;
    private int dailyPrice;

    public BookingLine(int lineId, int bookingId, int gearSetId, int qty, int dailyPrice) {
        this.lineId = lineId;
        this.bookingId = bookingId;
        this.gearSetId = gearSetId;
        this.qty = qty;
        this.dailyPrice = dailyPrice;
    }

    public int getLineId() {
        return lineId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getGearSetId() {
        return gearSetId;
    }

    public int getQty() {
        return qty;
    }

    public int getDailyPrice() {
        return dailyPrice;
    }

    public int calculateTotalPrice() {
        return qty * dailyPrice;
    }

    @Override
    public String toString() {
        return "BookingLine{" +
                "lineId=" + lineId +
                ", bookingId=" + bookingId +
                ", gearSetId=" + gearSetId +
                ", qty=" + qty +
                ", dailyPrice=" + dailyPrice +
                ", total=" + calculateTotalPrice() +
                '}';
    }
}
