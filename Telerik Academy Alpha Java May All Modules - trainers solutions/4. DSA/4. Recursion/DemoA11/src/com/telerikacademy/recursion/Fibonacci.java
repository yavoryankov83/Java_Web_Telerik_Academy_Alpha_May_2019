package com.telerikacademy.recursion;

public class Fibonacci {
    public static void main(String[] args) {
        // 0,1,1,2,3,5,8,13,21,...
        System.out.println(fibSlow(6));
        System.out.println(fibMemo(6));
        System.out.println(fibNoMem(6));
        System.out.println(fibSimple(6));
    }

    public static int fibSlow(int n) {
        if (n == 0 || n == 1)
            return n;

        return fibSlow(n - 1) + fibSlow(n - 2);
    }

    public static int fibMemo(int n) {
        return fibTail(n, new int[n + 1]);
    }

    public static int fibTail(int i, int[] memo) {
        // fill the cache
        if (memo[i] == 0) {
            // base case
            if (i == 0 || i == 1) {
                memo[i] = i;
            } else {
                // recursive step
                memo[i] = fibTail(i - 1, memo) + fibTail(i - 2, memo);
            }
        }

        return memo[i];
    }

    public static int fibNoMem(int n) {
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

    public static int fibSimple(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        int prev = 0;
        int curr = 1;

        for (int i = 2; i < n; i++) {
            int next = curr + prev;
            prev = curr;
            curr = next;
        }

        return curr + prev;
    }
}
