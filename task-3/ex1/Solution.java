package ex1;

public class Solution {
    public static void main(String[] args) {
        int numberOne = (new java.util.Random()).nextInt(899) + 100;
        int numberTwo = (new java.util.Random()).nextInt(899) + 100;
        int numberThree = (new java.util.Random()).nextInt(899) + 100;
        System.out.println("Number one: " + numberOne);
        System.out.println("Number two: " + numberTwo);
        System.out.println("Number three: " + numberThree);
        System.out.println("Summ of the first digits: " + (numberOne / 100 + numberTwo / 100 + numberThree / 100));
    }
}