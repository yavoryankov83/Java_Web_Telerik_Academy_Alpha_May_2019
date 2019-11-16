package com.telerikacademy;

public class Main {

    public static void main(String[] args) {
        String[] names = {"Pesho", "Gosho", "Ivana", "Misho", "Rumi"};
        int[] ages = {23, 30, 21, 25, 27};
        String[] phones = {"0887123456", "0887654321", "0887112233", "0888888888", "0885588111"};

        String[][] interests = {
                {"Football", "Computer Games"},
                {"Ski", "Climbing", "Programming"},
                {"Books", "Cooking"},
                {"Music", "Play drums", "Yoga"},
                {"Programming", "Dancing"}
        };

        // Advanced Task: implement friends
        int[][] friends = {
                {1, 3},
                {4},
                {0, 4},
                {0, 1, 2, 4},
                {2}
        };
        // Advanced Task

        for (int p = 0; p < names.length; p++) {
            introducePerson(names[p], ages[p]);
            sharePhone(phones[p]);
            showFriends(names, friends, p);

            System.out.println();
            System.out.println();
        }
    }

    public static void introducePerson(String name, int age) {
        System.out.printf("Hey, I am %s and I am %d years old :)\n", name, age);
    }

    public static void sharePhone(String phone) {
        System.out.printf("You can contact me at %s\n", phone);
    }

    public static void showFriends(String[] names, int[][] friends, int index) {
        System.out.print("My friends are: ");

        for (int f = 0; f < friends[index].length; f++) {
            System.out.printf("%s ", names[friends[index][f]]);
        }
    }
}