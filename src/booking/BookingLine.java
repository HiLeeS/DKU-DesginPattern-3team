package booking;

public class BookingLine {
    private int lineId; // PK
    private int bookingId;   // Booking과 연결
    private int gearSetId; // GearSet과 연결
    private int qty; // 수량
    private int dailyPrice; // 일일 가격

    public BookingLine(int lineId, int bookingId, int gearSetId, int qty, int dailyPrice) { // 생성자
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

    public int getDailyPrice() { // 일일 가격 반환 메서드
        return dailyPrice;
    }

    public int calculateTotalPrice() { // 총 가격 계산 메서드
        return qty * dailyPrice;
    }

    @Override
    public String toString() { // 객체 정보 출력 메서드
        return "booking.BookingLine{" +
                "lineId=" + lineId +
                ", bookingId=" + bookingId +
                ", gearSetId=" + gearSetId +
                ", qty=" + qty +
                ", dailyPrice=" + dailyPrice +
                ", total=" + calculateTotalPrice() +
                '}';
    }
}
