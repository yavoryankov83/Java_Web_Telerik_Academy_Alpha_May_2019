package com.telerikacademy;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static void fakeInput() {
        String test = "4\n" +
                "9 1";

        System.setIn(new ByteArrayInputStream(test.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        fakeInput();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        Arrays.sort(input);

        generate(input, length, new StringBuilder());

    }

    private static void generate(String[] input, int length, StringBuilder sb) {

        if (length == 0) {
            System.out.println(sb.toString());
        } else {

            for (String character : input) {
                sb.append(character);
                generate(input, length - 1, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
