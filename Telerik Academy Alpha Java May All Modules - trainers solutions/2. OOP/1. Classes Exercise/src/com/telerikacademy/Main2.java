package com.telerikacademy;

import java.util.ArrayList;

public class Main2 {
    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>();
        Person pesho = new Person("Pesho", 23, "0887123456", "Football", "Computer Games");
        Person gosho = new Person("Gosho", 30, "0887654321", "Ski", "Climbing", "Programming");
        Person ivana = new Person("Ivana", 21, "0887112233", "Books", "Cooking");
        Person misho = new Person("Misho", 25, "0888888888", "Music", "Play drums", "Yoga");
        Person rumi = new Person("Rumi", 27, "0885588111", "Programming", "Dancing");

        people.add(pesho);
        people.add(gosho);
        people.add(ivana);
        people.add(misho);
        people.add(rumi);

        pesho.addFriend(gosho);
        pesho.addFriend(misho);

        gosho.addFriend(rumi);

        ivana.addFriend(pesho);
        ivana.addFriend(rumi);

        misho.addFriend(pesho);
        misho.addFriend(gosho);
        misho.addFriend(ivana);
        misho.addFriend(rumi);

        rumi.addFriend(ivana);

        for (Person person : people) {
            person.introduce();
            person.sharePhone();
            person.showFriends();

            System.out.println();
        }
    }
}
