import payment.model.Payment;
import payment.strategy.CardPayment;
import payment.strategy.KakaoPayment;
import payment.strategy.NaverPayment;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Payment payment = new Payment(1, 101, 50000); // 더미 데이터

        System.out.print("결제 방식을 선택하세요 (카드 / 카카오페이 / 네이버페이): ");
        String input = scanner.nextLine().trim();

        switch (input) {
            case "카드":
                payment.setStrategy(new CardPayment());
                break;
            case "카카오페이":
                payment.setStrategy(new KakaoPayment());
                break;
            case "네이버페이":
                payment.setStrategy(new NaverPayment());
                break;
            default:
                System.out.println("올바르지 않은 결제 방식입니다.");
                scanner.close();
                return;
        }

        payment.pay();
        scanner.close();
    }
}