package com.telerikacademy.set;

import java.lang.reflect.Array;
import java.util.*;

public class TaskSolution {
// list: 2868100 ms
// list:  383100 ms
    public static void main(String[] args) {
        int[] nums = new int[]{12, 2, 6, 14, 8, 1, 5, 3, 12, 5};
        List<Integer> listNums = new ArrayList<>();
        listNums.add(12);
        listNums.add(2);
        listNums.add(6);
        listNums.add(14);
        listNums.add(8);
        listNums.add(1);
        listNums.add(5);
        listNums.add(3);
        listNums.add(12);
        listNums.add(5);

        int target = 13;

        solveWithList(nums, target);

        solveWithSet(listNums, target);

    }

    private static void solveWithSet(List<Integer> nums, int target) {
        long startTime = System.nanoTime();

        Set<Integer> set = new HashSet<>(nums);

        for (Integer num : set) {
            if (set.contains(target - num)) {
                System.out.printf("Pair: (%d, %d)\n", num, target - num);

                long endTime = System.nanoTime();
                System.out.println("With set: " + (endTime - startTime) + " milliseconds");
                break;
            }
        }

    }

    private static void solveWithList(int[] nums, int target) {
        long startTime = System.nanoTime();

        boolean flag = false;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    System.out.printf("Pair: (%d, %d)\n", nums[i], nums[j]);
                    flag = true;
                }
            }

            if (flag) {
                long endTime = System.nanoTime();
                System.out.println("With list: " + (endTime - startTime) + " milliseconds");
                break;
            }

        }

    }
}
