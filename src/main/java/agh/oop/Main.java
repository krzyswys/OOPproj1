package agh.oop;

import agh.oop.animal.Animal;
import agh.oop.map.Map;

public class Main {
    public static void main(String[] args) {
        Animal a = new Animal(new Map(),new Vector2d(0,0));
        System.out.println(a);
        a.move();
        System.out.println(a);
        a.move();
        System.out.println(a);
        a.move();
        System.out.println(a);
        a.move();
        System.out.println(a);
        a.move();
        System.out.println(a);
        a.move();
        System.out.println(a);
    }
}