package me.yong;

import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

    private static final Map<String, Integer> beverages = Map.of(
            "사이다", 1700,
            "콜라", 1900,
            "식혜", 2500,
            "솔의눈", 3000
    );

    public static void main(String[] args) {
        runVendingMachine();
    }

    private static void runVendingMachine() {
        Scanner scanner = new Scanner(System.in);
        displayBeverages();
        String customerChoice = getValidChoice(scanner);
        int price = beverages.get(customerChoice);
        int customerPaid = processPayment(scanner, price);

        printReceipt(customerChoice, price, customerPaid);

    }

    private static void displayBeverages() {
        System.out.println("📌 밴딩 머신 음료 목록:");
        beverages.forEach((name, price) -> System.out.printf("- %s: %d원\n", name, price));
        System.out.println();
    }

    private static String getValidChoice(Scanner scanner) {
        String choice = "";
        System.out.println("어떤 음료를 선택하시겠습니까?");
        while (!beverages.containsKey(choice)) {
            choice = scanner.nextLine().trim();
            if (!beverages.containsKey(choice)) {
                System.out.println("❌ 없는 상품입니다. 다시 입력하세요.");
            }
        }
        return choice;
    }

    private static int processPayment(Scanner scanner, int price) {
        int totalPaid = 0;
        System.out.printf("\n💰 %d원을 넣어주세요: \n", price);

        while (totalPaid < price) {
            System.out.print("투입 금액: ");
            totalPaid += scanner.nextInt();

            if (totalPaid < price) {
                System.out.printf("🚨 %d원이 부족합니다. 더 넣어주세요.\n", price - totalPaid);
            }
        }
        return totalPaid;
    }

    private static void printReceipt(String choice, int price, int paid) {
        int change = paid - price;
        System.out.println("\n✅ 구매 성공! 아래 영수증을 확인하세요.");
        System.out.println("-------------");
        System.out.printf("🛒 음료: %s (%d원)\n💵 투입한 금액: %d원\n💰 거스름돈: %d원\n", choice, price, paid, change);
        System.out.println("-------------");
    }
}
