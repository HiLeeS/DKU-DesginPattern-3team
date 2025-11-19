import booking.*;
import decorator.*;
import observer.*;
import observer.impl.*;
import observer.service.*;
import strategy.payment.*;
import strategy.pricing.*;
import factory.pricing.*;
import factory.pricing.PricingStrategyFactory;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class IntegrationDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== 🏕️ Camping Gear Rental System - 통합 테스트 시작 =====\n");

        // 1️⃣ 예약 생성 (Booking + BookingLine)
        System.out.println("--- [1단계: 예약 정보 입력] ---");
        System.out.print("사용자 ID를 입력하세요 (예: 1001): ");
        int userId = scanner.nextInt();
        System.out.print("대여할 장비 세트 ID를 입력하세요 (예: 1): ");
        int gearSetId = scanner.nextInt();
        System.out.print("수량을 입력하세요 (예: 1): ");
        int qty = scanner.nextInt();
        System.out.print("일일 대여료를 입력하세요 (예: 30000): ");
        int dailyPrice = scanner.nextInt();
        System.out.print("대여 기간(일)을 입력하세요 (예: 3): ");
        int days = scanner.nextInt();

        Booking booking = new Booking(101, userId, new Date(), new Date(), Status.PENDING);
        BookingLine line = new BookingLine(1, booking.getBookingId(), gearSetId, qty, dailyPrice);
        System.out.println("\n[1] 예약 생성 완료 → " + booking);
        System.out.println("    예약 항목 → " + line + "\n");

        // 2️⃣ 데코레이터 패턴: 장비 구성
        System.out.println("--- [2단계: 추가 옵션 선택] ---");
        System.out.print("램프를 추가하시겠습니까? (개수, 0이면 추가 안함): ");
        int lampCount = scanner.nextInt();
        System.out.print("의자를 추가하시겠습니까? (개수, 0이면 추가 안함): ");
        int chairCount = scanner.nextInt();
        System.out.print("보조배터리를 추가하시겠습니까? (개수, 0이면 추가 안함): ");
        int batteryCount = scanner.nextInt();

        Rentable rentable = new BaseRentalItem(gearSetId, line.getDailyPrice(), "패밀리 캠핑 세트");
        if (lampCount > 0) {
            rentable = new LampAddon(rentable, 7000, lampCount);
        }
        if (chairCount > 0) {
            rentable = new ChairAddon(rentable, 5000, chairCount);
        }
        if (batteryCount > 0) {
            rentable = new BatteryAddon(rentable, 3000, batteryCount);
        }

        int baseCost = rentable.cost(days, qty);
        System.out.println("\n[2] 장비 구성(Decorator)");
        System.out.println("    📦 구성: " + rentable.getDescription());
        System.out.println("    💰 기본 금액(" + days + "일 기준): " + baseCost + "원\n");

        // 3️⃣ 요금 계산 (Abstract Factory - Pricing)
        System.out.println("--- [3단계: 요금 계산] ---");
        System.out.println("성수기 여부를 선택하세요:");
        System.out.println("1: 성수기");
        System.out.println("2: 비성수기");
        System.out.print("선택: ");
        int peakChoice = scanner.nextInt();
        boolean isPeak = (peakChoice == 1);

        System.out.println("\nVIP 고객 여부를 선택하세요:");
        System.out.println("1: VIP");
        System.out.println("2: 일반");
        System.out.print("선택: ");
        int vipChoice = scanner.nextInt();
        boolean isVIP = (vipChoice == 1);

        System.out.println("\n장비 등급을 선택하세요:");
        System.out.println("1: HIGH");
        System.out.println("2: STANDARD");
        System.out.print("선택: ");
        int gradeChoice = scanner.nextInt();
        String grade = (gradeChoice == 1) ? "HIGH" : "STANDARD";

        PricingContext context = new PricingContext(days);
        context.setPeakSeason(isPeak);
        context.setEquipmentGrade(grade);

        RentalPolicyFactory policyFactory = PricingStrategyFactory.getRentalPolicyFactory(context, isVIP);
        DiscountStrategy discountStrategy = policyFactory.createDiscountStrategy();
        CancellationPolicy cancellationPolicy = policyFactory.createCancellationPolicy();

        IPricingStrategy pricing = new EquipmentGradePricingStrategy(
                new DiscountStrategyAdapter(discountStrategy), grade
        );
        int totalPrice = pricing.calculatePrice(baseCost, qty, days);
        System.out.println("\n[3] 요금 계산(Abstract Factory - Pricing)");
        System.out.println("    ⚙️ 적용 전략: " + discountStrategy.getClass().getSimpleName() + " + " + grade + " 등급 할증");
        System.out.println("    💰 최종 결제 금액: " + totalPrice + "원");
        System.out.println("    📋 취소 정책: " + cancellationPolicy.getClass().getSimpleName() + "\n");

        // 4️⃣ 결제 (Abstract Factory - Payment)
        System.out.println("--- [4단계: 결제] ---");
        System.out.println("결제 수단을 선택하세요:");
        System.out.println("1: CARD");
        System.out.println("2: KAKAO");
        System.out.println("3: NAVER");
        System.out.print("선택: ");
        int paymentChoice = scanner.nextInt();
        PaymentMethod paymentMethod;
        switch (paymentChoice) {
            case 1:
                paymentMethod = PaymentMethod.CARD;
                break;
            case 2:
                paymentMethod = PaymentMethod.KAKAO;
                break;
            case 3:
                paymentMethod = PaymentMethod.NAVER;
                break;
            default:
                System.out.println("잘못된 선택입니다. 기본값인 CARD로 설정합니다.");
                paymentMethod = PaymentMethod.CARD;
                break;
        }

        Payment payment = new Payment(2001, booking.getBookingId(), totalPrice);
        payment.setPaymentMethod(paymentMethod);
        System.out.println("\n[4] 결제 처리(Abstract Factory - Payment)");
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
        System.out.println("--- [5단계: 대여 및 반납 이벤트] ---");
        System.out.println("엔터 키를 입력하면 대여 이벤트를 발생시킵니다...");
        scanner.nextLine(); // Consume the leftover newline
        scanner.nextLine();
        System.out.println("\n[5] 대여 이벤트 발생(Observer)");
        rentalService.rent(
                line.getGearSetId(),
                booking.getUserId(),
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(days),
                qty
        );

        // 7️⃣ 반납 이벤트 발생
        System.out.println("\n엔터 키를 입력하면 반납 이벤트를 발생시킵니다...");
        scanner.nextLine();
        System.out.println("\n[6] 반납 이벤트 발생(Observer)");
        returnService.returnGear(
                line.getGearSetId(),
                booking.getUserId(),
                LocalDateTime.now().plusDays(days)
        );

        System.out.println("\n===== ✅ 테스트 종료: 모든 패턴이 정상 동작했습니다. =====");
        scanner.close();
    }
}
