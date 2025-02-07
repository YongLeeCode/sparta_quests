package me.yong;


import java.util.Random;

public class Main {

    public static String createRandomName() {
        final String[] part1 = {"기철초풍", "멋있는", "재미있는"};
        final String[] part2 = {"도전적인", "노란색의", "바보같은"};
        final String[] part3 = {"돌고래", "개발자", "오랑우탄"};
        Random random = new Random();

        return part1[random.nextInt(3)] + " "
                + part2[random.nextInt(3)] + " "
                + part3[random.nextInt(3)];
    }

    public static void main(String[] args) {
        String name = createRandomName();
        System.out.println(name);
    }
}