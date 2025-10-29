package decorator;

public class ChairAddon extends AddonDecorator {
    private int unitPrice;
    private int count;

    public ChairAddon(Rentable inner, int unitPrice, int count) {
        super(inner);
        this.unitPrice = unitPrice;
        this.count = count;
    }

    @Override
    public String getDescription() {
        return inner.getDescription() + " + 의자x" + count;
    }

    @Override
    public int cost(int days, int qty) {
        return inner.cost(days, qty) + (unitPrice * count * days);
    }
}
