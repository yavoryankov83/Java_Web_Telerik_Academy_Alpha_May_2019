package com.telerikacademy.tasks;

import java.util.Scanner;
import java.util.Stack;

public class Brackets {

//5 * (123 * (1 + 3) + ((4 - 3) / 6))

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        Stack<Integer> expressionsIndices = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                expressionsIndices.push(i);
            } else if (str.charAt(i) == ')') {
                System.out.println(str.substring(expressionsIndices.pop(), i + 1));
            }
        }


    }
}
