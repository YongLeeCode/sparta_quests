package me.yong;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class FindingWord {

    private static final List<String> words = List.of("airplane", "apple", "arm", "bakery", "banana", "bank", "bean",
            "belt", "bicycle", "biography", "blackboard", "boat", "bowl", "broccoli", "bus", "car", "carrot", "chair",
            "cherry", "cinema", "class", "classroom", "cloud", "coat", "cucumber", "desk", "dictionary", "dress", "ear",
            "eye", "fog", "foot", "fork", "fruits", "hail", "hand", "head", "helicopter", "hospital", "ice", "jacket",
            "kettle", "knife", "leg", "lettuce", "library", "magazine", "mango", "melon", "motorcycle", "mouth",
            "newspaper", "nose", "notebook", "novel", "onion", "orange", "peach", "pharmacy", "pineapple", "plate",
            "pot", "potato", "rain", "shirt", "shoe", "shop", "sink", "skateboard", "ski", "skirt", "sky", "snow",
            "sock", "spinach", "spoon", "stationary", "stomach", "strawberry", "student", "sun", "supermarket",
            "sweater", "teacher", "thunderstorm", "tomato", "trousers", "truck", "vegetables", "vehicles", "watermelon",
            "wind");
    private static HashMap<Character, Boolean> chars = new HashMap<>();
    private static Scanner scan = new Scanner(System.in);

    public static void play() {
        int life = 10;
        String word = chooseWord();
        findAnswer(word);

        while (life > 0) {
            System.out.printf("현재 남은 기회: %d\n", life);
            System.out.print("a-z 중 하나만 입력해주세요: ");
            String input = scan.nextLine().toLowerCase();
            boolean ok = isValidInput(input);
            life -= matchLetter(ok, input);
            String currWord = getCurrentWord(chars, word);
            displayCurrentWord(currWord, life);

            if (currWord.equals(word)) {
                break;
            }
        }

        if (life > 0) {
            System.out.print("정답입니다!");
        } else {
            System.out.print("이런 기회를 다 쓰셨군요. 다음 게임에 힘내봐요");
        }
    }

    private static void displayCurrentWord(String currWord, int life) {
        System.out.println();
        System.out.println(currWord);
    }

    private static int matchLetter(boolean ok, String input) {
        if (ok && chars.containsKey(input.charAt(0))) {
            chars.put(input.charAt((0)), true);
            return 0;
        } else if (ok && !chars.containsKey(input.charAt(0))) {
            System.out.println("정답 포함된 알파벳이 아닙니다. 기회가 1 차감 됩니다.");
            return 1;
        }
        return 0;
    }

    private static boolean isValidInput(String input) {
        if (input.length() != 1) {
            System.out.print("\n글자 하나만 입력해주세요.");
            return false;
        }
        if (input.charAt(0) < 'a' || input.charAt(0) > 'z') {
            System.out.print("\n소문자 알파벳이 아닙니다.");
            return false;
        }
        if (chars.containsKey(input.charAt(0)) && chars.get(input.charAt(0))) {
            System.out.print("\n이미 찾은 글자군요. 다른 글자를 찾아보세요.");
            return false;
        }
        return true;
    }

    private static String getCurrentWord(HashMap<Character, Boolean> answer, String word) {
        StringBuilder curr = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (answer.get(word.charAt(i))) {
                curr.append(word.charAt(i));
            } else {
                curr.append("_");
            }
        }
        return curr.toString();
    }

    private static void findAnswer(String word) {
        for (int i = 0; i < word.length(); i++) {
            chars.put(word.charAt(i), false);
        }
    }

    private static String chooseWord() {
        Random random = new Random();
        int index = random.nextInt(words.size());
        return words.get(index);
    }

    public static void main(String[] args) {
        play();
    }
}
