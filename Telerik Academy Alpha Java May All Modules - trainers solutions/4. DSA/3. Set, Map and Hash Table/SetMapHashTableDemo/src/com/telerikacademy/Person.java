package com.telerikacademy;

import java.util.Objects;

public class Person implements Comparable<Person>{

    String firstName;
    String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("in equals");
        Person p = (Person) obj;
        return firstName.equals(p.getFirstName()) &&
                lastName.equals(p.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public int compareTo(Person o) {
        if (this.firstName.compareTo(o.firstName) != 0) {
            return this.firstName.compareTo(o.firstName);
        }
        return this.lastName.compareTo(o.lastName);
    }
}
