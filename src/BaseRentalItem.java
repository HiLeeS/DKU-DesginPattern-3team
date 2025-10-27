public class BaseRentalItem implements Rentable {
    private int gearSetId;
    private int dailyPrice;
    private String description;

    public BaseRentalItem(int gearSetId, int dailyPrice, String description) {
        this.gearSetId = gearSetId;
        this.dailyPrice = dailyPrice;
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int cost(int days, int qty) {
        return dailyPrice * days * qty;
    }
}
