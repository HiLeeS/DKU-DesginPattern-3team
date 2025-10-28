package pricing;

public class PeakSeasonPricingStrategy implements IPricingStrategy {
    // 성수기 할증 상수: 20%
    private static final double PEAK_SEASON_MULTIPLIER = 1.2;

    @Override
    public int calculatePrice(int basePrice, int qty, int days) {
        return (int) (basePrice * qty * days * PEAK_SEASON_MULTIPLIER);
    }
}
