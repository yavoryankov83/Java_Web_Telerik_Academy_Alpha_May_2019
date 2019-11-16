package com.telerikacademy.recursion;

import java.io.File;

public class LectureMain {
    public static void main(String[] args) {

        /*Demo 2 - print stars both with loop and with recursion*/

        // printStars(5);
        /*Demo 3 - Show output before and after recursive call*/
        // printNumber(5);
        /*Demo 4 - Factorial*/
//        System.out.println(fact(4));
        /*Demo 5 - Powers*/
        /*Demo 6 - Iterative Factorial*/
        /*Demo 7 - Recursive Fibonacci*/
        /*Demo 8 - Fibonacci with memoization*/
        /*Demo 9 - Fibonacci with memoization & iteration*/
        /*Demo 10 - Fibonacci with iteration*/
        /*Demo 11 - isPalindrome*/
        /*Demo 11 - isPalindrome*/
        /*Demo 12 - Hailstone*/
        System.out.println(reverseString("Telerik"));
    }

    /*Demo 2 - print stars both with loop and with recursion*/
//    public static void printStars(int n) {
//        if (n <= 0) {
//
//            return;
//        }
//        System.out.print("* ");
//        if (n == 1) {
//            return;
//        }
//
//        printStars(n - 1);
//    }
//
//    public static void printNumber(int n) {
//        if (n <= 0) {
//            return;
//        }
//        //System.out.println(n);
//        printNumber(n - 1);
//        System.out.println(n);
//    }

//    public static int fact(int n){
//        if (n <= 1){
//            return 1;
//        }
//
//        int lessFact = fact(n - 1);
//        System.out.println(lessFact);
//        return n * lessFact;
//    }


    public static int PowerOfN(int power, int base) {
        int number;
        if (power <= 0) {
            return 1;
        }
        number = base * PowerOfN(power - 1, base);
        return number;

    }

    public static String reverseString(String str) {
        if (str.length() <= 1) {
            return str;
        }
        int lastIndex = str.length();
        String lastLetter = str.substring(lastIndex - 1); // the last letter
        String reverseRemain = reverseString(str.substring(0, lastIndex - 1));
        return lastLetter + reverseRemain;
    }
}