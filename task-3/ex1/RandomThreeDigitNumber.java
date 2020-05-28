package ex1;

public class RandomThreeDigitNumber {
    public static int getRandomThreeDigitNumber() {
        return (new java.util.Random()).nextInt(899) + 100;
    }
}
