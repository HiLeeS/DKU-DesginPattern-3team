package decorator;

public class Main {
    public static void main(String[] args) {

        System.out.println("==== [1] 기본 세트 + 의자 + 램프 + 배터리 ====");
        Rentable rental1 =
                new ChairAddon(
                        new LampAddon(
                                new BatteryAddon(
                                        new BaseRentalItem(1, 30000, "텐트 세트"),
                                        3000, 1
                                ),
                                7000, 1
                        ),
                        5000, 2
                );
        printResult(rental1, 2, 1);


        System.out.println("\n==== [2] 캠핑 의자 세트 + 램프만 ====");
        Rentable rental2 =
                new LampAddon(
                        new BaseRentalItem(2, 20000, "캠핑 의자 세트"),
                        7000, 1
                );
        printResult(rental2, 3, 1);


        System.out.println("\n==== [3] 솔로 캠핑 세트 + 배터리 + 의자 ====");
        Rentable rental3 =
                new ChairAddon(
                        new BatteryAddon(
                                new BaseRentalItem(3, 25000, "솔로 캠핑 세트"),
                                3000, 2
                        ),
                        5000, 1
                );
        printResult(rental3, 1, 1);
    }

    private static void printResult(Rentable rental, int days, int qty) {
        System.out.println("📦 구성: " + rental.getDescription());
        System.out.println("💰 총 금액 (" + days + "일 기준): " + rental.cost(days, qty) + "원");
    }
}
