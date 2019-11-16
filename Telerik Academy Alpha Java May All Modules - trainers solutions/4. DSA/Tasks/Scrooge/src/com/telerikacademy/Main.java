package com.telerikacademy;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int rows, cols;

    private static int startX, startY;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int[] possibleCol = {-1, +1, 0, 0};
    private static int[] possibleRow = {0, 0, -1, +1};

    private static void fakeInput() {
        String test = "4 3\n" +
                "3 2 4\n" +
                "2 0 3\n" +
                "1 1 5\n" +
                "2 2 5";

        System.setIn(new ByteArrayInputStream(test.getBytes()));
    }

    public static void main(String[] args) throws IOException {

        fakeInput();

        String[] input = br.readLine().split(" ");

        rows = Integer.parseInt(input[0]);
        cols = Integer.parseInt(input[1]);

        int[][] matrix = new int[rows][cols];

        fillMatrix(matrix);

        System.out.println(calculateCoins(startX, startY, matrix));
    }

    private static int calculateCoins(int startX, int startY, int[][] matrix) {
        int max = 0;

        int nextRow = 0, nextCol = 0;

        for (int i = 0; i < possibleRow.length; i++) {
            int result = movementHelper(
                    startX + possibleRow[i],
                    startY + possibleCol[i], matrix);
            if (result > max) {
                max = result;
                nextRow = startX + possibleRow[i];
                nextCol = startY + possibleCol[i];
            }
        }

        if (max == 0) return 0;

        matrix[nextRow][nextCol]--;

        return 1 + calculateCoins(nextRow, nextCol, matrix);

    }

    private static int movementHelper(int currentRow, int currentCol, int[][] matrix) {
//        if (isInRange(currentRow, currentCol)) {
//            return matrix[currentRow][currentCol];
//        }
//        return 0;
        return isInRange(currentRow, currentCol) ? matrix[currentRow][currentCol] : 0;
    }

    private static boolean isInRange(int currentRow, int currentCol) {
        return !(currentRow < 0 || currentCol < 0
                || currentRow >= rows || currentCol >= cols);
    }

    private static void fillMatrix(int[][] matrix) throws IOException {
        String[] input;
        for (int i = 0; i < rows; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Integer.parseInt(input[j]);

                if (matrix[i][j] == 0) {
                    startX = i;
                    startY = j;
                }

            }
        }
    }
}
