package decorator;

public abstract class AddonDecorator implements Rentable {
    protected Rentable inner;

    public AddonDecorator(Rentable inner) {
        this.inner = inner;
    }

    @Override
    public String getDescription() {
        return inner.getDescription();
    }

    @Override
    public int cost(int days, int qty) {
        return inner.cost(days, qty);
    }
}
