package agh.oop;

import agh.oop.animal.Animal;
import agh.oop.map.Map;

public class Main {
    public static void main(String[] args) {
        Animal a = new Animal(new Map(),new Vector2d(4,4));
        System.out.println(a);
        for( int i = 0 ; i < 10 ; ++i ) {
            a.move();
            System.out.println(a);
        }

    }
}