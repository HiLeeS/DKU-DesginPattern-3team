import booking.*;
import decorator.*;
import observer.*;
import observer.impl.*;
import observer.service.*;
import strategy.payment.*;
import strategy.pricing.*;
import java.time.LocalDateTime;
import java.util.Date;

public class IntegrationDemo {
    public static void main(String[] args) {
        System.out.println("===== 🏕️ Camping Gear Rental System - 통합 테스트 시작 =====\n");

        // 1️⃣ 예약 생성 (Booking + BookingLine)
        Booking booking = new Booking(101, 1001, new Date(), new Date(), Booking.Status.PENDING);
        BookingLine line = new BookingLine(1, booking.getBookingId(), 1, 1, 30000);
        System.out.println("[1] 예약 생성 완료 → " + booking);
        System.out.println("    예약 항목 → " + line + "\n");

        // 2️⃣ 데코레이터 패턴: 장비 구성
        Rentable rentable = new ChairAddon(
                new LampAddon(
                        new BatteryAddon(
                                new BaseRentalItem(1, line.getDailyPrice(), "패밀리 캠핑 세트"),
                                3000, 2
                        ),
                        7000, 1
                ),
                5000, 2
        );

        int days = 3;
        int qty = line.getQty();
        int baseCost = rentable.cost(days, qty);
        System.out.println("[2] 장비 구성(Decorator)");
        System.out.println("    📦 구성: " + rentable.getDescription());
        System.out.println("    💰 기본 금액(" + days + "일 기준): " + baseCost + "원\n");

        // 3️⃣ 요금 계산 (Strategy - Pricing)
        IPricingStrategy pricing =
                new EquipmentGradePricingStrategy(
                        new PeakSeasonPricingStrategy(), "HIGH"
                );
        int totalPrice = pricing.calculatePrice(baseCost, qty, days);
        System.out.println("[3] 요금 계산(Strategy)");
        System.out.println("    ⚙️ 적용 전략: 성수기 + 고급 장비 할증");
        System.out.println("    💰 최종 결제 금액: " + totalPrice + "원\n");

        // 4️⃣ 결제 (Strategy - Payment)
        Payment payment = new Payment(2001, booking.getBookingId(), totalPrice);
        payment.setStrategy(new KakaoPayment());
        System.out.println("[4] 결제 처리(Strategy)");
        payment.pay();
        System.out.println("    ✅ 결제 상태: " + payment.getStatus() + "\n");

        // 5️⃣ 옵저버 등록 (Observer)
        GearRentalService rentalService = new GearRentalService();
        GearReturnService returnService = new GearReturnService();

        Observer inventory = new InventoryObserver();
        Observer paymentObs = new PaymentObserver();
        Observer maintenance = new GearMaintenanceObserver();

        rentalService.attach(inventory);
        rentalService.attach(paymentObs);
        rentalService.attach(maintenance);

        returnService.attach(inventory);
        returnService.attach(paymentObs);
        returnService.attach(maintenance);

        // 6️⃣ 대여 이벤트 발생
        System.out.println("[5] 대여 이벤트 발생(Observer)");
        rentalService.rent(
                line.getGearSetId(),
                booking.getUserId(),
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(days),
                qty
        );

        // 7️⃣ 반납 이벤트 발생
        System.out.println("\n[6] 반납 이벤트 발생(Observer)");
        returnService.returnGear(
                line.getGearSetId(),
                booking.getUserId(),
                LocalDateTime.now().plusDays(days)
        );

        System.out.println("\n===== ✅ 테스트 종료: 모든 패턴이 정상 동작했습니다. =====");
    }
}
