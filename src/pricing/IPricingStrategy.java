package pricing;

public interface IPricingStrategy {
    public int calculatePrice(int basePrice, int qty, int days);
}
