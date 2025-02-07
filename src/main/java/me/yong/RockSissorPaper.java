package me.yong;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class RockSissorPaper {

    private static final Map<Integer, String> PRIZE = Map.of(
            0, "꽝",
            1, "곰돌이 인형",
            2, "스파르타 랜드 입장권",
            3, "스파르타 캐니언 항공 투어권",
            4, "호텔 스파르타 숙박권",
            5, "스파르테이트 항공권"
    );
    private static final int[][] GET_ROUND_RESULT = {
            {0, -1, 1},
            {1, 0, -1},
            {-1, 1, 0}
    };

    private static final Map<Integer, String> CHOICE = Map.of(
            0, "주먹",
            1, "가위",
            2, "보"
    );

    private static void PlayGame() {
        displayStartingGame();
        int win = 0;
        for (int i = 1; i <= 5; i++) {
            System.out.printf("Round %d! \n", i);
            int computer = getComputerChoice();
            int user = getUserChoice();
            int roundResult = GET_ROUND_RESULT[computer][user];
            win = roundResult == 1 ? win + roundResult : win;
            displayRoundResult(computer, user, roundResult, win);
        }
        displayTotalResult(win);
    }

    private static void displayTotalResult(int win) {
        System.out.printf("당신은 총 %d번 승리하셨습니다.\n",win);
        System.out.printf("경품은 %s입니다.", PRIZE.get(win));
    }

    private static void displayRoundResult(int computer, int user, int roundResult, int win) {
        System.out.println("----------------");
        System.out.println("You vs Computer");
        System.out.printf("%s vs %s \n", CHOICE.get(user), CHOICE.get(computer));
        switch (roundResult) {
            case 1 -> System.out.println("승리하셨습니다!");
            case -1 -> System.out.println("패배하셨습니다!");
            case 0 -> System.out.println("비기셨습니다!");
            default -> System.out.println("오류가 있는것 같아요.");
        }
        System.out.printf("현재 승리 횟수 : %d\n", win);
        System.out.println("----------------\n");
    }

    private static int getUserChoice() {
        Scanner s = new Scanner(System.in);
        String choice;
        do {
            System.out.println("'가위' '바위' '보' 중 하나를 입력해주세요.");
            System.out.print("Your choice: ");
            choice = s.nextLine().toLowerCase();
            System.out.println();

            if (!choice.equals("가위") && !choice.equals("바위") && !choice.equals("보")) {
                System.out.println("잘못된 입력입니다!");
            }
        } while (!choice.equals("가위") && !choice.equals("바위") && !choice.equals("보"));
        return switch (choice) {
            case "가위" -> 1;
            case "보" -> 2;
            default -> 0;
        };
    }

    private static int getComputerChoice() {
        Random random = new Random();
        int choice = random.nextInt(3);
        return switch (choice) {
            case 1 -> 1;
            case 2 -> 2;
            default -> 0;
        };
    }

    private static void displayStartingGame() {
        System.out.println("가위! 바위! 보! 게임을 시작하겠습니다!");
        System.out.println("총 5번의 가위바위보를 진행하며 몇번을 승리하냐에 따라 경품이 달라집니다.");
        System.out.println("---------------경품 안내---------------");
        for (int i = 0; i <= 5; i++) {
            System.out.printf("%d번 승리 시, %s \n", i, PRIZE.get(i));
        }
        System.out.println("------------------------------------");
    }

    public static void main(String[] args) {
        PlayGame();
    }
}
