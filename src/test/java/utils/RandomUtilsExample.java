package utils;

import static utils.RandomUtils.*;

public class RandomUtilsExample {
    public static void main(String[] args) {
        System.out.println("Random string of 10 letters: "+ getRandomString(10));
        System.out.println("Random int: "+ getRandomInt(1234,999999));//значение переменных
        System.out.println("Random email "+ getRandomEmail());
    }
}
