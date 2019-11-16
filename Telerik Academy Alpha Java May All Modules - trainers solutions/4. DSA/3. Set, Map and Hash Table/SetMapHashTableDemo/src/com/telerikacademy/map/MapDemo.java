package com.telerikacademy.map;

import com.telerikacademy.Person;

import java.util.*;
import java.util.stream.Collectors;

public class MapDemo {
    public static void main(String[] args) {

        Person person = new Person("Ivan", "Dimitrov");
        Person person1 = new Person("Tim", "Mim");
        Person person2 = new Person("Tim", "Mim");

        Map<Person, Integer> map = new HashMap<>();

        map.put(person, 21);
        map.put(person1, 34);

        System.out.println(map);

        map.put(person2, 37);

        System.out.println(map);

        map.putIfAbsent(person2, 70);

        System.out.println(map);

        System.out.println(map.getOrDefault(person, 0));
        System.out.println(map.getOrDefault(person2, 0));

        Set<Person> setOfKeys = new HashSet<>(map.keySet());

        setOfKeys.remove(person);

        System.out.println(map);

        map.putIfAbsent(new Person("Peter", "Ognyanov"), 19);

        System.out.println(setOfKeys);

        Map<Person, Integer> sortedMap = new TreeMap<>(map);

        System.out.println(sortedMap);

        sortedMap.putIfAbsent(new Person("Mitko", "Mitashki"), 21);

        System.out.println(sortedMap
                .values()
                .stream()
                .filter(integer -> integer >= 21)
                .collect(Collectors.toList()));

        System.out.println(sortedMap
                .entrySet()
                .stream()
                .filter(p -> p.getValue() >= 21)
                .collect(Collectors.toList()));

        for (Map.Entry<Person, Integer> entry : sortedMap.entrySet()) {
            System.out.println("Person: " + entry.getKey() + " Age: " + entry.getValue());
        }

        List<Map.Entry<Person, Integer>> neshtoKatoMap = new ArrayList<>();
    }
}
