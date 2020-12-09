package com.example.reflectiondemo;

public class Person {

    public String name;
    private int age;

    public Person() {

    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void print(int i) {
        System.out.println("我在写数字： " + i);
    }

    public static void say(String str) {
        System.out.println("我在说： " + str);
    }

    public String toString() {
        return "姓名： " + this.name;
    }
}
