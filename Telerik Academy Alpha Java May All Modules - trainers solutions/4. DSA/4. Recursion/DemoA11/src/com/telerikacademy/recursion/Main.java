package com.telerikacademy.recursion;

import java.io.File;
import java.io.FilenameFilter;

public class Main {

    public static void main(String[] args) {


        /*Demo 1 - List Files to show real life recursion*/
        /*Demo 2 - print stars both with loop and with recursion*/
        /*Demo 3 - Show output before and after recursive call*/
        /*Demo 4 - Factorial*/
        /*Demo 5 - Powers*/
        /*Demo 6 - Iterative Factorial*/
        /*Demo 7 - Recursive Fibonacci*/
        /*Demo 8 - Fibonacci with memoization*/
        /*Demo 9 - Fibonacci with memoization & iteration*/
        /*Demo 10 - Fibonacci with iteration*/
        /*Demo 11 - isPalindrome*/
        /*Demo 12 - Hailstone*/

    }

    /*Demo 1 - List Files to show real life recursion*/
    public static void demoListFiles() {
        System.out.println(countTxt(new File("mycomputer")));
    }

    public static int countTxt(File dir) {
        File[] files = dir.listFiles();
        if (files.length == 0)
            return 0;

        int count = 0;

        for (File file : files) {
            if (file.isFile()) {
                if (file.getName().endsWith(".txt"))
                    count++;
            } else {
                count += countTxt(file);
            }
        }
        return count;
    }

    /*Demo 2 - print stars both with loop and with recursion*/
    // Prints the given number of stars on the console.
    // Assumes n >= 1
    public static void printStars(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.print("*");
        }
    }

    public static void printStarsRecursive(int n) {
        if (n == 1) {
            System.out.print("*");
            return;
        }

        System.out.print("*");
        printStarsRecursive(n - 1);
    }

    /*Demo 3 - Show output before and after recursive call*/
    public static void printNumbers(int n) {
        if (n == 0) {
            return;
        }

        // Each previous call waits for the next call to finish
        // printNumbers(n-1);
        System.out.println(n);
        printNumbers(n - 1);
    }

    /*Demo 4 - Factorial*/
    public static int fact(int n) {
        if (n == 1) {
            return 1;
        }

        return n * fact(n - 1);
    }

    /*Demo 5 - Powers*/
    public static int power(int x, int n) {
        if (n == 0) {
            return 1;
        }

        return x * power(x, n - 1);
    }

    /*Demo 6 - Iterative Factorial*/
    public static int factIterative(int n) {
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }

        return res;
    }

    /*Demo 7 - Recursive Fibonacci*/
    public static int fibonacciSlow(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        return fibonacciSlow(n - 1) + fibonacciSlow(n - 2);
    }

    /*Demo 8 - Fibonacci with memoization*/
    public static int fibonacci(int n) {
        // Array is zero-based, so we need to make sure n is valid index
        return fibonacciMemo(n, new int[n + 1]);
    }

    private static int fibonacciMemo(int i, int[] memo) {
        // We are okay with i starting at 0, because array is zero-based
        if (i == 0 || i == 1) {
            return i;
        }

        if (memo[i] == 0) {
            memo[i] = fibonacciMemo(i - 1, memo) + fibonacciMemo(i - 2, memo);
        }

        return memo[i];
    }

    /*Demo 9 - Fibonacci with memoization & iteration*/
    public static int fibonacci1(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int[] memo = new int[n];

        memo[0] = 0;
        memo[1] = 1;

        for (int i = 2; i < n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }

        return memo[n - 1] + memo[n - 2];
    }

    /*Demo 10 - Fibonacci with iteration*/
    public static int fibonacci2(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        int prev = 0;
        int current = 1;

        for (int i = 2; i <= n; i++) {
            int calc = prev + current;
            prev = current;
            current = calc;
        }

        return current;
    }

    /*Demo 11 - isPalindrome*/
    public static boolean isPalindrome(String str) {
        int len = str.length();
        if (len == 0 || len == 1) {
            return true;
        }

        return str.charAt(0) == str.charAt(len - 1) && isPalindrome(str.substring(1, len - 1));
    }

    /*Demo 12 - Hailstone*/
    public static void hailstone(int n) {
        System.out.println(n);
        if (n == 1) {
            return;
        }

        if (n % 2 == 0) {
            hailstone(n / 2);
        } else {
            hailstone(3 * n + 1);
        }
    }
}
