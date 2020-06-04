package ex1;

public class Solution {
    public static void main(String[] args) {
        calculateSummOfFirstDigits();
    }

    public static void calculateSummOfFirstDigits () {
        int summ = 0;
        for (int i = 0; i < 3; i++) {
            int number = RandomThreeDigitNumber.getRandomThreeDigitNumber();
            summ += number / 100;
            System.out.println(number);
        }
        System.out.println("Summ of the first digits: " + summ);
    }
}