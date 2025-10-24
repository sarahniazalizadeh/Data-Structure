package org.example;

public class Person implements Comparable<Person>, HashValueGenerator<Person> {

    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
        return 0;
    }

    @Override
    public int computeHashValue(Person key) {
        return 0;
    }
}
