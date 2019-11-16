package com.telerikacademy;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Cipher {

    private static void fakeInput() {
        String test = "1122\n" +
                "A1B12C11D2";
        System.setIn(new ByteArrayInputStream(test.getBytes()));
    }

    private static Map<String, Character> letters = new HashMap<>();
    private static Set<String> result = new TreeSet<>();

    public static void main(String[] args) throws IOException {

        fakeInput();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String code = br.readLine();
        String cipher = br.readLine();

        decipher(cipher);

        decode(code, "");

        System.out.println(result.size());
        result.forEach(System.out::println);

    }

    private static void decode(String code, String answer) {
        if (code.length() == 0) {
            result.add(answer);
        } else {
            for (int i = 0; i < code.length(); i++) {
                String current = code.substring(0, i + 1);
                if (letters.containsKey(current)) {
                    decode(code.substring(i + 1), answer + letters.get(current));
                }
            }
        }
    }


    private static void decipher(String cipher) {
        //A1B12C11D2
        for (int i = cipher.length() - 1; i >= 0; i--) {
            if (isLetter(cipher.charAt(i))) {
                letters.put(cipher.substring(i + 1), cipher.charAt(i));
                cipher = cipher.substring(0, i);
            }
        }

    }

    private static boolean isLetter(char character) {
        return character >= 'A' && character <= 'Z';
    }
}
